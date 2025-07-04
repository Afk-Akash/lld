package src.strategy.payment

import java.math.BigDecimal

class CardPaymentStrategy: PaymentStrategy {
    override fun pay(amount: BigDecimal) {
        println("Paid by card $amount")
    }
}