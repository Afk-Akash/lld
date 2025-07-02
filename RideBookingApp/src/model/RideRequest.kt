package src.model

import java.math.BigDecimal

data class RideRequest(
    val id: String,
    val riderId: String,
    val driverId: String?,
    val fromLocation: Location,
    val toLocation: Location,
    var fare: BigDecimal,
    val otp: Int
)