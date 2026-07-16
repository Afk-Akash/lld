package factory;

import strategy.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitServiceBuilder {

    Map<Integer, RateLimitStrategy> map = new ConcurrentHashMap<>();


    public RateLimitStrategy getRateLimitStrategyOrchestrator(String strategyName, int userId){
        if(map.get(userId) == null){
            System.out.println("hello");
            RateLimitStrategy strategy = getRateLimitStrategy(strategyName);
            map.put(userId, strategy);
            return strategy;
        }
        else return map.get(userId);
    }

    private RateLimitStrategy getRateLimitStrategy(String strategyName){
        Queue<Integer> queue = new LinkedList<>();
        return switch (strategyName) {
            case "fixedWindowStrategy" -> new FixedWindowStrategy();
            case "slidingWindowStrategy" -> new SlidingWindowStrategy();
            case "leakyBucketStrategy" -> new LeakyBucketStrategy(queue);
            default -> new TokenBucketStrategy();
        };
    }
}
