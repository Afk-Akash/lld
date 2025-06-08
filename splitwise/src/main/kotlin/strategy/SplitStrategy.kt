package org.splitwise.strategy

import org.splitwise.model.User
import java.math.BigDecimal

interface SplitStrategy {
    fun split(
        totalAmount: BigDecimal,
        payer: User,
        splits: Map<User, BigDecimal>
    ): Map<User, BigDecimal>
}