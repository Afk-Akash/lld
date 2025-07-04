package service

import entity.TokenBucket
import request.RequestContext
import strategy.BucketTokenStrategy
import strategy.SlidingWindowStrategy
import java.time.Instant
import java.util.LinkedList
import java.util.Queue

class RateLimitService(
    private val slidingWindowStrategy: SlidingWindowStrategy,
    private val tokenBucketStrategy: BucketTokenStrategy
) {
    val timeWindow = 1
    val maxAllowedRequest = 10

    val userRequestLog = mutableMapOf<String, Queue<Long>>()

    fun isRequestAllowed(typeOfRateLimit: Int, userId: String): Boolean {
        val rateLimiterStrategy = if(typeOfRateLimit == 1){
            tokenBucketStrategy
        } else {
            slidingWindowStrategy
        }

        val requestBody = RequestContext(
            userId =  userId,
            timeStamp = Instant.now().toEpochMilli()
        )
       return rateLimiterStrategy.isRequestAllowed(requestBody)
    }
}