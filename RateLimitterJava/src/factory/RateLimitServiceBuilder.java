package factory;

import strategy.*;

public class RateLimitServiceBuilder {
    FixedWindowStrategy fixedWindowStrategy;
    SlidingWindowStrategy slidingWindowStrategy;
    TokenBucketStrategy tokenBucketStrategy;
    LeakyBucketStrategy leakyBucketStrategy;

    public RateLimitServiceBuilder(
            FixedWindowStrategy fixedWindowStrategy,
            SlidingWindowStrategy slidingWindowStrategy,
            TokenBucketStrategy tokenBucketStrategy,
            LeakyBucketStrategy leakyBucketStrategy
    ) {
        this.fixedWindowStrategy = fixedWindowStrategy;
        this.slidingWindowStrategy = slidingWindowStrategy;
        this.tokenBucketStrategy = tokenBucketStrategy;
        this.leakyBucketStrategy = leakyBucketStrategy;
    }


    public RateLimitStrategy getRateLimitStrategy(String strategyName){
        return switch (strategyName) {
            case "fixedWindowStrategy" -> fixedWindowStrategy;
            case "slidingWindowStrategy" -> slidingWindowStrategy;
            case "leakyBucketStrategy" -> leakyBucketStrategy;
            default -> tokenBucketStrategy;
        };

    }
}
