package strategy;

import request.RequestContext;

public interface RateLimitStrategy {
    boolean isRequestAllowed( RequestContext request);
}
