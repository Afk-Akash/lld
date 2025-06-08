package org.splitwise.service

import org.splitwise.controller.ExpenseController
import org.splitwise.controller.GroupController
import org.splitwise.controller.UserController
import org.splitwise.model.User
import org.splitwise.strategy.EqualSplit
import org.splitwise.strategy.PercentageSplit
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class SplitWiseSystemService(
    private val userController: UserController,
    private val groupController: GroupController,
    private val expenseController: ExpenseController
) {

    fun init() {
        while (true) {
            startMenu()
            when (readln().trim()) {
                "1" -> handleCreateUser()
                "2" -> handleCreateGroup()
                "3" -> handleAddExpense()
                "4" -> handleViewBalances()
                "5" -> handleSettlement()
                "6" -> viewExpenseLog()
                "7" -> {
                    println("👋 Exiting SplitWise. Bye!")
                    return
                }

                else -> println("❌ Invalid input. Try again.")
            }

        }
    }

    private fun viewExpenseLog() {
        println("please enter group id in which u want to see the log")
        val groupId = readln()
        expenseController.viewExpense(groupId = groupId)
    }

    private fun handleSettlement() {
        println("Enter user who want to settle his due")
        val userId = readln()
        val settler = userController.getUser(userId)
        println("Enter userId with whom he want to settle ")
        val payerId = readln()
        if(userId == payerId) {
            println("user and payer are selected same")
            return
        }
        val toWhom = userController.getUser(payerId)

        println("Enter amount")
        val amount = readln().toBigDecimal()
        if(amount <= BigDecimal.ZERO){
            println("Nice try diddy, amount should be greater than Zero")
            return
        }
        if(settler == null || toWhom == null){
            println("either or the user not found")
            return
        }
        println("Enter group in which you want to settle")
        val groupId = readln()

        expenseController.settleDue(settler, toWhom, amount, groupId)

    }

    private fun startMenu() {
        println("\n🎉 Welcome to SplitWise CLI")
        println("1. Create User")
        println("2. Create Group")
        println("3. Add Expense")
        println("4. View Balances")
        println("5. settle amount")
        println("6. view expense in group")
        println("7. exit")
        print("Enter choice: ")
    }

    private fun handleCreateUser() {
        println("\n👤 Create New User")
        print("Enter name: ")
        val name = readln().trim()
        print("Enter email: ")
        val email = readln().trim()
        print("Enter phone: ")
        val phone = readln().trim()

        val user = userController.createUser(name, email, phone)
        println("✅ User created with ID: ${user!!.userId}")
    }

    private fun handleCreateGroup() {
        println("\n👥 Create New Group")
        print("Enter group name: ")
        val groupName = readln().trim()
        print("Enter user IDs (space-separated): ")
        val memberIds: List<String> = readln().trim().split(" ")

        val members = mutableListOf<User>()

        memberIds.forEach { id ->
            val user = userController.getUser(id)
            if (user != null) {
                members.add(user)
            }
        }

        groupController.createGroup(members, groupName)

        println("✅ Group '$groupName")
    }

    private fun handleAddExpense() {
        println("\n💸 Add Expense")
        println("Please enter expense name")
        val expenseName = readln()
        print("Enter group ID: ")
        val groupId = readln().trim()
        val group = groupController.getGroup(groupId) ?: throw IllegalArgumentException("Group not found with this Id")

        print("Enter payer user ID: ")
        val payerId = readln().trim()
        val payer = userController.getUser(payerId) ?: throw IllegalArgumentException("This user is not found")

        print("Enter total amount: ")
        val amount = readln().trim().toBigDecimal()

        println("Choose Split Type:\n1. EQUAL\n2. EXACT\n3. PERCENT")
        print("Enter choice [1-3]: ")
        val splitType = when (readln().trim()) {
            "1" -> "EQUAL"
            "3" -> "PERCENT"
            else -> {
                println("❌ Invalid split type.")
                return
            }
        }

        val members = groupController.getGroupMembers(groupId)
        println("Group Members: ${members.joinToString(", ")}")


        val splitStrategy = when (splitType.uppercase()) {
            "EQUAL" -> EqualSplit()
            "PERCENT" -> PercentageSplit()
            else -> return
        }

        val splits: Map<User, BigDecimal> = when (splitType) {
            "EQUAL" -> {
                val share = amount.divide(BigDecimal(members.size),2, RoundingMode.HALF_UP)
                members.associateWith { share }
            }
            "PERCENT" -> {
                println("Enter percentages for each member (space-separated):")
                val percentages = readln().trim().split(" ").map { it.toBigDecimal() }
                if (percentages.size != members.size) {
                    println("❌ Number of percentages doesn't match group members!")
                    return
                }
                val totalPercent = percentages.sumOf{it}
                if (totalPercent.compareTo(BigDecimal(100)) != 0) {
                    println("❌ Total percentage must be 100!")
                    return
                }
                val calculatedSplits = percentages.map { percent ->
                    val payable = amount.multiply(percent).divide(BigDecimal(100))
                    println("payable $payable")
                    payable
                }
                val userAmountMap = mutableMapOf<User, BigDecimal>()

                for (i in members.indices) {
                    val user = members[i]
                    val share = calculatedSplits[i]
                    userAmountMap[user] = share
                }
                userAmountMap
            }

            else -> return
        }

        expenseController.addExpense(
            expenseName = expenseName,
            payer = payer,
            group = group,
            amount = amount,
            splitStrategy = splitStrategy,
            splits = splits
        )
        println("✅ Expense added to group ${group.groupName}")
    }

    private fun handleViewBalances() {
        println("\n📊 View Balances")
        println("1. View All Balances")
        println("2. View Group Balances")
        println("3. View User Balance")
        print("Enter choice: ")

        when (readln().trim()) {
            "1" -> expenseController.showAllBalances()
            "2" -> {
                print("Enter group ID: ")
                val groupId = readln().trim()
                expenseController.showGroupBalances(groupId)
            }

            "3" -> {
                print("Enter user ID: ")
                val userId = readln().trim()
                val user = userController.getUser(userId)
                if (user == null) {
                    println("User not found with given Id")
                    return
                }
                expenseController.showUserBalance(user)
            }

            else -> println("❌ Invalid input.")
        }
    }
}