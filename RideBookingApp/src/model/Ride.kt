package src.model

import src.model.enum.RideStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Ride(
    val id: String,
    val riderId: String,
    val driverId: String,
    val fromLocation: Location,
    var status: RideStatus = RideStatus.IN_PROGRESS,
    val toLocation: Location,
    val rideStartTime: LocalDateTime,
    var rideEndTime: LocalDateTime?,
    val fare: BigDecimal
) {
    companion object {
        fun fromRideRequest(rideRequest: RideRequest, startTime: LocalDateTime) = Ride(
            id = UUID.randomUUID().toString(),
            riderId = rideRequest.riderId,
            driverId = rideRequest.driverId!!,
            fromLocation = rideRequest.fromLocation,
            toLocation = rideRequest.toLocation,
            rideStartTime = startTime,
            rideEndTime = null,
            fare = rideRequest.fare
        )
    }
}
