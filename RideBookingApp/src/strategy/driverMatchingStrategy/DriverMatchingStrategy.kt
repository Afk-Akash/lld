package src.strategy.driverMatchingStrategy

import src.model.RideRequest

interface DriverMatchingStrategy {
    fun findDrivers(rideRequest: RideRequest)
}