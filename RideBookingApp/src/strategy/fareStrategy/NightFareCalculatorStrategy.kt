package src.strategy.fareStrategy

import src.model.RideRequest
import java.math.BigDecimal

class NightFareCalculatorStrategy(
    val baseFare: BigDecimal = 30.toBigDecimal()
): FareCalculatorStrategy {
    override fun calculateFare(
        rideRequest: RideRequest
    ): BigDecimal {
        TODO("Not yet implemented")
    }

}