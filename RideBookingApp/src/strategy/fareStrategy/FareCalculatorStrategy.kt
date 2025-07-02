package src.strategy.fareStrategy

import src.model.Location
import src.model.RideRequest
import java.math.BigDecimal

interface FareCalculatorStrategy {
    fun calculateFare(rideRequest: RideRequest): BigDecimal

}