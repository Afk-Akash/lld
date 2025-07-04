package strategy

import request.RequestContext
import java.time.Instant
import java.util.LinkedList
import java.util.Queue

class SlidingWindowStrategy(
    val timeWindow: Int = 1,
    val maxAllowedRequest: Long = 10
): RateLimiterStrategy {

    val userRequestLog = mutableMapOf<String, Queue<Long>>()

    override fun isRequestAllowed(requestContext: RequestContext): Boolean {
        val userId = requestContext.userId
        val currentTime = Instant.now().epochSecond
        val windowStartTime = currentTime - timeWindow

        val queue = userRequestLog.getOrPut(userId) { LinkedList() }

        while (queue.isNotEmpty() && queue.peek() < windowStartTime) {
            queue.poll()
        }
        return if(queue.size < maxAllowedRequest){
            queue.offer(currentTime)
            println("allow")
            true
        } else {
            println("Block")
            false
        }
    }

}