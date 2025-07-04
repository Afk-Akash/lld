package src.strategy.fareStrategy

import src.model.enum.FareTimeType
import java.time.LocalTime

class FareFactoryBuilder {

    fun getFareStrategy(): FareCalculatorStrategy {
        val currentTime = LocalTime.now()

        return when (getFareTimeType(currentTime)) {
            FareTimeType.NIGHT -> NightFareCalculatorStrategy()
            FareTimeType.PEAK_MORNING -> SurgeFareCalculatorStrategy()
            FareTimeType.PEAK_EVENING -> SurgeFareCalculatorStrategy()
            FareTimeType.NORMAL -> BasicFareCalculatorStrategy()
        }
    }

    private fun getFareTimeType(currentTime: LocalTime = LocalTime.now()): FareTimeType {
        return when {
            currentTime.isAfter(LocalTime.of(22, 0))
                    ||
                    currentTime.isBefore(LocalTime.of(5, 0)) -> FareTimeType.NIGHT

            currentTime.isAfter(LocalTime.of(8, 59))
                    &&
                    currentTime.isBefore(LocalTime.of(11, 1)) -> FareTimeType.PEAK_MORNING

            currentTime.isAfter(LocalTime.of(17, 29))
                    &&
                    currentTime.isBefore(LocalTime.of(19, 31)) -> FareTimeType.PEAK_EVENING

            else -> FareTimeType.NORMAL
        }
    }

}