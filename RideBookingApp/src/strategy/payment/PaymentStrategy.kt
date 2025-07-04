package src.strategy.payment

import java.math.BigDecimal

interface PaymentStrategy {
    fun pay(amount: BigDecimal)
}