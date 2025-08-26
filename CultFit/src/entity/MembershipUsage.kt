package entity

data class MembershipUsage(
    val userId: String,
    val month: String,
    var confirmedCount: Int
)
