package factory;

import strategy.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitServiceBuilder {

    Map<String, RateLimitStrategy> map = new ConcurrentHashMap<>();


    public RateLimitStrategy getRateLimitStrategyOrchestrator( int userId, String apiPath){
        String key = userId + "-" + apiPath;
        return map.computeIfAbsent(key, k -> getRateLimitStrategy(apiPath));
    }

    private RateLimitStrategy getRateLimitStrategy(String apiPath){
        Queue<Integer> queue = new LinkedList<>();
        return switch (apiPath) {
            case "/api/fund-transfer" -> new FixedWindowStrategy();
            case "/api/get-balance" -> new SlidingWindowStrategy();
            case "/api/loan-decision" -> new LeakyBucketStrategy(queue);
            default -> new TokenBucketStrategy();
        };
    }
}
