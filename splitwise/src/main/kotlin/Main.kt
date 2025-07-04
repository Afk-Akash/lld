package org.splitwise

import org.splitwise.controller.ExpenseController
import org.splitwise.controller.GroupController
import org.splitwise.controller.UserController
import org.splitwise.model.BalanceSheet
import org.splitwise.model.User
import org.splitwise.service.SplitService
import org.splitwise.service.SplitWiseSystemService

fun main() {
    val allUsers = listOf(
        User(userId = "u1", name = "Akash", email = "akash@example.com", mobileNo = "1111111111"),
        User(userId = "u2", name = "Neha", email = "neha@example.com", mobileNo = "2222222222"),
        User(userId = "u3", name = "Ashish", email = "ashish@example.com", mobileNo = "3333333333"),
        User(userId = "u4", name = "Vikrant", email = "vikrant@example.com", mobileNo = "4444444444"),
        User(userId = "u5", name = "Pranav", email = "pranav@example.com", mobileNo = "5555555555")
    )

    val userController = UserController()
    allUsers.forEach { user ->
        userController.createUser(user)
    }

    val balanceSheet = BalanceSheet()
    val expenseController = ExpenseController(balanceSheet)
    val groupController= GroupController(balanceSheet)

    val splitWiseSystemService = SplitWiseSystemService(
        userController = userController,
        groupController = groupController,
        expenseController = expenseController
    )

    splitWiseSystemService.init()
}