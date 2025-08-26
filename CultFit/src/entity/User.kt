package entity

import enums.MembershipType
import java.time.LocalDate

data class User(
    val userId: Long,
    val name: String,
    val password: String,
    val membershipType: MembershipType,
    val membershipExpiryDate: LocalDate,
    val isAdmin: Boolean
)
