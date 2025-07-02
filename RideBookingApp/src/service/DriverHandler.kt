package src.service

import src.model.Driver
import src.model.RideRequest
import src.strategy.notifications.NotificationStrategy

class DriverHandler(
    val notificationStrategy: NotificationStrategy
) {


    fun acceptRide(rideRequest: RideRequest, driver: Driver) {
        //pre validation
        if(!rideRequest.driverId.isNullOrBlank()) {
            println("This ride has already been accepted by someone else")
            return
        }
        rideRequest.driverId = driver.id

        notificationStrategy.notifyRider(rideRequest)
    }

}