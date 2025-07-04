package src.service

import src.model.Location
import src.model.RideRequest
import src.model.Rider
import src.strategy.fareStrategy.FareCalculatorStrategy

class RiderHandler(
    val rideService: RideService,
    val fareCalculatorService: FareCalculatorService
) {
    fun rideRequest( rideRequest: RideRequest) {
        val fare = fareCalculatorService.calculateFare(rideRequest)
        rideRequest.fare = fare

        rideService.rideRequest(rideRequest)
    }

    fun cancelRideRequest(rideRequest: RideRequest) {
        rideService.cancelRideRequest(rideRequest)
    }

    fun updateRideRequest(rider: Rider, rideRequest: RideRequest, updatedDropLocation: Location) {
        TODO()
    }

}