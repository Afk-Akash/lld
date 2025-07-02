package src.service

import src.model.RideRequest
import src.strategy.driverMatchingStrategy.DriverMatchingStrategy
import src.strategy.notifications.NotificationStrategy

class RideService(
    val driverMatchingStrategy: DriverMatchingStrategy,
    val notificationStrategy: NotificationStrategy
) {

    fun initialize() {
        println("....Application Started....")
        println("Please choose your choice")
        println("1. I am a rider")
        println("2. I am a Driver")

        val riderOrDriver = readln()

    }

    fun rideRequest(rideRequest: RideRequest){
        val availableDrivers = driverMatchingStrategy.findDrivers(rideRequest)

        notificationStrategy.notifyDrivers(availableDrivers)
    }
}
