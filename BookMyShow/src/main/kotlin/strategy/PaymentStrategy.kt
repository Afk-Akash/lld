package org.bookMyShow.strategy

import java.math.BigDecimal

interface PaymentStrategy {
    fun pay(amount: BigDecimal): Boolean
}
