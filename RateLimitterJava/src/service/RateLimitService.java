package service;

import factory.RateLimitServiceBuilder;
import request.RequestContext;
import strategy.RateLimitStrategy;

public class RateLimitService {
    private final RateLimitServiceBuilder rateLimitServiceBuilder;

    public RateLimitService(RateLimitServiceBuilder rateLimitServiceBuilder) {
        this.rateLimitServiceBuilder = rateLimitServiceBuilder;
    }

    public boolean isRequestAllowed(String strategyName, RequestContext requestContext, int userId) {
        RateLimitStrategy strategy = rateLimitServiceBuilder.getRateLimitStrategyOrchestrator(userId, "/fund-transfer");
        return strategy.isRequestAllowed(requestContext);
    }
}
