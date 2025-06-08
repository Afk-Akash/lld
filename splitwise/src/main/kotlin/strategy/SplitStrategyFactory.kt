package org.splitwise.strategy

class SplitStrategyFactory {
    fun getSplitStrategy(strategy: String): SplitStrategy{
        return when(strategy) {
            "EQUAL" -> EqualSplit()
            "PERCENT" -> PercentageSplit()
            else -> {
                println("Invalid strategy")
                throw IllegalArgumentException("Invalid strategy")
            }
        }
    }
}