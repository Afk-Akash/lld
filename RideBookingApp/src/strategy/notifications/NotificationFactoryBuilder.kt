package src.strategy.notifications

import src.model.enum.FareTimeType
import src.strategy.fareStrategy.BasicFareCalculatorStrategy
import src.strategy.fareStrategy.FareCalculatorStrategy
import src.strategy.fareStrategy.NightFareCalculatorStrategy
import src.strategy.fareStrategy.SurgeFareCalculatorStrategy
import java.time.LocalTime

class NotificationFactoryBuilder {

    fun getNotificationStrategy(type: Int): NotificationStrategy {

        return when (type) {
            1 -> KafkaNotificationSend()
            2 -> PushNotificationSend()
            3 -> SmsNotificationSend()
            else -> {
                throw Exception("This type of error is not supported yet")
            }
        }
    }

}