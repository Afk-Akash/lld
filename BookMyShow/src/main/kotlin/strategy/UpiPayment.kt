package org.bookMyShow.strategy

import java.math.BigDecimal

class UpiPayment: PaymentStrategy {
    override fun pay(amount: BigDecimal): Boolean {
        println("payment was done using upi of $amount")
        return true
    }
}