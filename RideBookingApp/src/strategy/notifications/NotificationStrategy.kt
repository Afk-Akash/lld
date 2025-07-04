package src.strategy.notifications

import src.model.Driver
import src.model.RideRequest

interface NotificationStrategy {
    fun notifyDrivers(drivers: List<Driver>)

    fun notifyDriver(driver: Driver)

    fun notifyRider(rideRequest: RideRequest)
}