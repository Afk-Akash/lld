package src.service

import src.model.Driver
import src.model.Location
import src.model.RideRequest
import src.model.Rider
import src.strategy.fareStrategy.FareCalculatorStrategy

class RiderHandler(
    val fareCalculatorStrategy: FareCalculatorStrategy,
    val rideService: RideService
) {
    fun rideRequest(rider: Rider, rideRequest: RideRequest): Driver? {
        val fare = fareCalculatorStrategy.calculateFare(rideRequest)
        rideRequest.fare = fare

        rideService.rideRequest(rideRequest)
    }

    fun cancelRideRequest(rider: Rider, rideRequest: RideRequest) {
        TODO()
    }

    fun updateRideRequest(rider: Rider, rideRequest: RideRequest, updatedDropLocation: Location) {
        TODO()
    }

}