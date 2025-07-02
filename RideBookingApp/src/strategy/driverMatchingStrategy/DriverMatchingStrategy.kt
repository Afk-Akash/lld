package src.strategy.driverMatchingStrategy

import src.model.Driver
import src.model.RideRequest

interface DriverMatchingStrategy {
    fun findDrivers(rideRequest: RideRequest): List<Driver>
}