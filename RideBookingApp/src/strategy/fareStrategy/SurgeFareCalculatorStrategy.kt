package src.strategy.fareStrategy

import src.model.RideRequest
import java.math.BigDecimal

class SurgeFareCalculatorStrategy(
    val baseFare: BigDecimal = 15.toBigDecimal(),
    val fareMultiplier : BigDecimal = 1.5.toBigDecimal()
): FareCalculatorStrategy {
    override fun calculateFare(
        rideRequest: RideRequest
    ): BigDecimal {
        TODO("Not yet implemented")
    }

}