package org.splitwise.model

import org.splitwise.strategy.SplitStrategy
import java.math.BigDecimal

data class Expense(
    val name: String,
    val type: String?,
    val amount: BigDecimal,
    val splits: List<User>,
    val splitStrategy: SplitStrategy,
    val groupId: String? = null
)