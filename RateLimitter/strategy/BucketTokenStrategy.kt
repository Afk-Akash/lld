package strategy

import entity.TokenBucket
import request.RequestContext
import java.time.Instant

class BucketTokenStrategy(
    val tokenBucket: TokenBucket,
    val tokenFillRate: Int,
    val capacity: Long
): RateLimiterStrategy {


    override fun isRequestAllowed(requestContext: RequestContext): Boolean {
        val currentTime = Instant.now().toEpochMilli()

        val timeWindow = currentTime-tokenBucket.lastPushed
        val tokenToFill = (timeWindow*tokenFillRate)/1000

        return if(tokenBucket.tokens > 0) {
            val totalToken = minOf(tokenToFill+tokenBucket.tokens, capacity)
            tokenBucket.tokens = maxOf(totalToken, tokenBucket.tokens)
            tokenBucket.lastPushed = currentTime
            println("Bucket - Allow")

            tokenBucket.tokens--
            true
        }else{
            println("Bucket - Block")
            false
        }
    }

}