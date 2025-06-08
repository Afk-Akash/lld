package org.splitwise.strategy

import org.splitwise.model.User
import java.math.BigDecimal
import java.math.RoundingMode

class EqualSplit: SplitStrategy {
    override fun split(
        totalAmount: BigDecimal,
        payer: User,
        splits: Map<User, BigDecimal>
    ): Map<User, BigDecimal> {
        val memberCount = splits.size
        val share = totalAmount.divide(BigDecimal(memberCount), 2, RoundingMode.HALF_UP)

        return splits.keys
            .filter { it != payer }
            .associateWith { share }
    }
}