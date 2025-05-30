package org.bookMyShow.controller

import org.bookMyShow.strategy.PaymentStrategy
import java.math.BigDecimal

class PaymentController(
    private val paymentStrategy: PaymentStrategy
) {
    fun executePayment(amount: BigDecimal): Boolean = paymentStrategy.pay(amount)
}