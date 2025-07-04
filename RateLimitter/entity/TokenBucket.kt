package entity

data class TokenBucket(
    var tokens: Long,
    var lastPushed: Long
)
