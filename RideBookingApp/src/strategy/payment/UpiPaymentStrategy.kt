package src.strategy.payment

import java.math.BigDecimal

class UpiPaymentStrategy: PaymentStrategy {
    override fun pay(amount: BigDecimal) {
        println("Paid by upi successfully $amount")
    }
}