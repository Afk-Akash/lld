package src.strategy.payment

import src.strategy.notifications.KafkaNotificationSend
import src.strategy.notifications.NotificationStrategy
import src.strategy.notifications.PushNotificationSend
import src.strategy.notifications.SmsNotificationSend

class PaymentFactoryBuilder(
   val cardPaymentStrategy: CardPaymentStrategy,
   val upiPaymentStrategy: UpiPaymentStrategy
) {
    fun getPaymentStrategy(type: Int): PaymentStrategy {

        return when (type) {
            1 -> cardPaymentStrategy
            2 -> upiPaymentStrategy
            else -> {
                throw Exception("This type of payment method $type is not supported yet")
            }
        }
    }
}