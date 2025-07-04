package strategy

import request.RequestContext

interface RateLimiterStrategy {
    fun isRequestAllowed(requestContext: RequestContext): Boolean
}