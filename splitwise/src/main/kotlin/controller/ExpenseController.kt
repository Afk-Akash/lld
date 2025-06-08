package org.splitwise.controller

import org.splitwise.model.BalanceSheet
import org.splitwise.model.Expense
import org.splitwise.model.Group
import org.splitwise.model.User
import org.splitwise.strategy.SplitStrategy
import java.math.BigDecimal

class ExpenseController(
    private val balanceSheet: BalanceSheet
) {

    private val expenseLog = mutableListOf<Expense>()

    fun settleDue(settler: User, toWhom: User, amount: BigDecimal, groupId: String) {
        balanceSheet.settleAmount(settler, toWhom, amount, groupId)
    }

    fun addExpense(
        expenseName: String,
        payer: User,
        group: Group,
        amount: BigDecimal,
        splitStrategy: SplitStrategy,
        splits: Map<User, BigDecimal>
    ) {
        if (!group.users.contains(payer)) {
            throw IllegalArgumentException("❌ Payer is not part of the group")
        }

        val owedMap = splitStrategy.split(
            totalAmount = amount,
            payer = payer,
            splits = splits
        )

        for ((user, owedAmount) in owedMap) {
            balanceSheet.updateGroupBalance(payer, user, owedAmount, group.groupId)
        }
        val expense = Expense(
            name = expenseName,
            type = "NA",
            amount = amount,
            splits = group.users.toList(),
            splitStrategy = splitStrategy,
            groupId = group.groupId
        )
        expenseLog.add(expense)
        balanceSheet.adjustAllUserBalanceInGroup(groupId = group.groupId)

        println("✅ Expense of ₹$amount added by ${payer.name} in group ${group.groupName}")
    }

    fun showAllBalances() {
        balanceSheet.showGlobalBalance()
    }

    fun showGroupBalances(groupId: String) {
        balanceSheet.showGroupBalance(groupId)
    }

    fun showUserBalance(user: User) {
        balanceSheet.showBalanceOfUser(user)
    }

    fun viewExpense(groupId: String){
        expenseLog.forEach { expense ->
            if(expense.groupId == groupId){
                println("expense name - ${expense.name} of amount ${expense.amount}")
            }
        }
    }
}
