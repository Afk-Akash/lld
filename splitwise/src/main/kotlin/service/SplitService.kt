package org.splitwise.service

import org.splitwise.model.User
import org.splitwise.strategy.EqualSplit
import org.splitwise.strategy.PercentagSplit
import org.splitwise.strategy.SplitStrategy
import java.math.BigDecimal

class SplitService{

    fun splitAmount(amount: BigDecimal,payee: User, users: List<User>) {
        println("Please choose the strategy you want to split - 1. Equally 2.Percentage")

        val splitStrategy = readlnOrNull()
        if (splitStrategy != null) {
            if(splitStrategy < "1" || splitStrategy > "2"){
                throw IllegalArgumentException("Wrong selection")
            }

        }
        val strategy: SplitStrategy = when(splitStrategy){
            "1" -> EqualSplit()
            "2" -> PercentagSplit()
            else -> throw IllegalArgumentException("wrong selection")
        }

        strategy.split()

    }
}