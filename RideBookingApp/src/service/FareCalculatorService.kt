package src.service

import src.model.RideRequest
import src.strategy.fareStrategy.FareFactoryBuilder
import java.math.BigDecimal

class FareCalculatorService(
    val fareFactoryBuilder: FareFactoryBuilder
) {

    fun calculateFare(rideRequest: RideRequest): BigDecimal {
        val fareCalculatorStrategy = fareFactoryBuilder.getFareStrategy()

        return fareCalculatorStrategy.calculateFare(rideRequest)
    }
}