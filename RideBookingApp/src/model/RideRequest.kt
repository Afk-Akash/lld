package src.model

import src.model.enum.RideRequestStatus
import java.math.BigDecimal

data class RideRequest(
    val id: String,
    val riderId: String,
    var driverId: String?,
    val fromLocation: Location,
    val toLocation: Location,
    var fare: BigDecimal,
    val otp: Int,
    var status: RideRequestStatus = RideRequestStatus.SEARCHING
)