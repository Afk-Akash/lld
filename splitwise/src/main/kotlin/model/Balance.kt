package org.splitwise.model

import java.math.BigDecimal

data class Balance(
    val total: BigDecimal,
    val owe: BigDecimal,
    val owed: BigDecimal
)
