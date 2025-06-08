package org.splitwise.strategy

import org.splitwise.model.User
import java.math.BigDecimal

class PercentageSplit : SplitStrategy {
    override fun split(
        totalAmount: BigDecimal,
        payer: User,
        splits: Map<User, BigDecimal>
    ): Map<User, BigDecimal> {
        val totalEntered = splits.values.sumOf{ it }
        if(totalEntered.compareTo(totalAmount) != 0) {
            throw IllegalArgumentException("❌ Exact amounts don't sum up to total amount!")
        }

        return splits
            .filterKeys { it != payer }
    }

}
