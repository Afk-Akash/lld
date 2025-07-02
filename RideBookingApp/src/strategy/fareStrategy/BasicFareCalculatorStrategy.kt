package src.strategy.fareStrategy

import src.model.RideRequest
import java.math.BigDecimal

class BasicFareCalculatorStrategy(
    val baseFare: BigDecimal = 10.toBigDecimal()
): FareCalculatorStrategy {
    override fun calculateFare(
        rideRequest: RideRequest
    ): BigDecimal {
        TODO("Not yet implemented")
    }

}