package org.bookMyShow.strategy

import java.math.BigDecimal

class CcAvenuePayment: PaymentStrategy {
    override fun pay(amount: BigDecimal): Boolean {
        println("Payment was done via ccavenue of $amount")
        return true
    }
}