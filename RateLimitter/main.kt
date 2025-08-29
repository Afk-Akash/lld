import entity.TokenBucket
import service.RateLimitService
import kotlinx.coroutines.*
import strategy.BucketTokenStrategy
import strategy.SlidingWindowStrategy
import java.time.Instant

fun main() {
    runBlocking {
        val tokenBucket = TokenBucket(
            tokens = 20,
            lastPushed = Instant.now().toEpochMilli()-1000
        )

        val typeOfRateLimit = 1
        val tokenBucketStrategy = BucketTokenStrategy(tokenBucket, 3, 30)

        val slidingWindowStrategy = SlidingWindowStrategy()
        val svc =
            RateLimitService(slidingWindowStrategy = slidingWindowStrategy, tokenBucketStrategy = tokenBucketStrategy)

        launch {
            repeat(40) {
                svc.isRequestAllowed(typeOfRateLimit, "1")
            }
        }

        launch {
            repeat(10) {
                svc.isRequestAllowed(typeOfRateLimit, "1")
//                delay(10)
            }
        }
    }
}