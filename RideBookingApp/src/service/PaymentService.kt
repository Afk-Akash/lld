package src.service

import src.strategy.payment.PaymentFactoryBuilder
import java.math.BigDecimal

class PaymentService(
    val paymentFactoryBuilder: PaymentFactoryBuilder
) {

    fun initiatePayment(amount: BigDecimal, paymentType: Int) {
        val paymentStrategy = paymentFactoryBuilder.getPaymentStrategy(paymentType)

        paymentStrategy.pay(amount)
    }

}