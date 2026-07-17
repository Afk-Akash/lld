import factory.RateLimitServiceBuilder;
import request.RequestContext;
import service.ExecutorLeakyBucketService;
import service.RateLimitService;
import strategy.FixedWindowStrategy;
import strategy.LeakyBucketStrategy;
import strategy.SlidingWindowStrategy;
import strategy.TokenBucketStrategy;

import java.nio.file.InvalidPathException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    /*
    4 algorithms for rate limiting
    1. fixed window
    2. sliding window
    3. token bucket
    4. leaky bucket

    1. fixed window algorithm allows us to use a fix number of request to process.
    for example -> 100 req/min
     12:00 - 12:01 = 100req
     12:01 - 12:02 = 100req
     12:02 - 12:03 = 100req

     12:00:59 -> 100 req burst ( ok ) -> server handle it cause 100 burst is allowed
     12:01:00 -> token reset again allow 100 req -> again 100req burst (server see 200 req under 2 sec) server cant handle

     ^ this is problem with fixed window

     to fix it we have 2nd algorithm sliding window algorithm

     2. requests are allowed based on % of time in current window which lies in previous window
     for example -> 100 req/min
     12:00 - 12:01 = 100req
     12:01 - 12:02 = 100req
     12:02 - 12:03 = 100req

     12:00:59 -> 100 req burst (ok)
     12:00:15 -> 50 req -> sliding window sees in current window only 15 sec has passed rest 45 sec is still in prev window
     expected req -> 50 + (45/60)*100  = 125 > 100 ( block)
     so here we are making sure load is evenly distributed instead of twice burst in same window length
     Question - but you can still burst 100req at a time ??

     3. Token bucket -> we fill the token with fixed rate and requests will go through if bucket has token
     for example filling rate 2 req/s and cap of bucket is 100
     But you can still burst of 100 req at a time and once token is empty req will be blocked

     4. Leaky bucket ->
     Here we process request with our pace for example 1 rps
     we will put all the request in queue and process 1rps if quest is full of the max cap req will be dropped
     in this way server won't get burst traffic but more it works where req can be treated as async

     */
    public static boolean process(RateLimitService rateLimitService, int id, String strategyName, int userId) {
        RequestContext requestContext = new RequestContext(id);

        boolean allowed = rateLimitService.isRequestAllowed(strategyName, requestContext, userId);
        System.out.println(LocalDateTime.now().getSecond() + ": Request allowed for id " + id + " : " + allowed);
        return allowed;
    }
    public static void main(String[] args){

        System.out.println("app start");

        RateLimitServiceBuilder builder = new RateLimitServiceBuilder();

        RateLimitService rateLimitService = new RateLimitService(builder);
        RequestContext requestContext = new RequestContext(5);

        boolean isAllow = rateLimitService.isRequestAllowed("na", requestContext, 2);


        ExecutorLeakyBucketService executorLeakyBucketService = new ExecutorLeakyBucketService();





        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            ExecutorService childA = Executors.newFixedThreadPool(10);
            AtomicInteger cnt = new AtomicInteger();
            for (int i = 0; i < 130; i++) {
                int id = i;
                childA.submit(() -> {
                    childA.submit(() -> {
                        boolean allowed =  process(rateLimitService, id, "tokenBucketStrategy", 1);
                        if(allowed) cnt.getAndIncrement();
                    });
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("tokenBucketStrategy: total true --> " + cnt);
            childA.shutdown();
        });

        executor.submit(() -> {
            ExecutorService childB = Executors.newFixedThreadPool(10);
            AtomicInteger cnt = new AtomicInteger();
            for (int i = 0; i < 130; i++) {
                int id = i;
                childB.submit(() -> {
                    boolean allowed =  process(rateLimitService, id, "fixedWindowStrategy", 2);
                    if(allowed) cnt.getAndIncrement();
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("fixedWindowStrategy: total true --> " + cnt);
            childB.shutdown();
        });


        executor.submit(() -> {
            ExecutorService childC = Executors.newFixedThreadPool(10);
            AtomicInteger cnt = new AtomicInteger();
            for (int i = 0; i < 130; i++) {
                int id = i;
                childC.submit(() -> {
                    boolean allowed =  process(rateLimitService, id, "slidingWindowStrategy", 3);
                    if(allowed) cnt.getAndIncrement();
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("slidingWindowStrategy: total true --> " + cnt);
            childC.shutdown();
        });



        executor.submit(() -> {
            ExecutorService childD = Executors.newFixedThreadPool(10);
            AtomicInteger cnt = new AtomicInteger();
            for (int i = 0; i < 130; i++) {
                int id = i;
                childD.submit(() -> {
                    boolean allowed =  process(rateLimitService, id, "leakyBucketStrategy", 4);
                    if(allowed) cnt.getAndIncrement();
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("leakyBucketStrategy: total true --> " + cnt);
            childD.shutdown();
        });


        executor.shutdown();
    }

}

