package org.splitwise

import org.splitwise.model.User
import org.splitwise.service.SplitService

fun main() {
    val allUsers = listOf(
        User(userId = "U1", name = "Akash", email = "akash@example.com", mobileNo = "1111111111"),
        User(userId = "U2", name = "Neha", email = "neha@example.com", mobileNo = "2222222222"),
        User(userId = "U3", name = "Ashish", email = "ashish@example.com", mobileNo = "3333333333"),
        User(userId = "U4", name = "Vikrant", email = "vikrant@example.com", mobileNo = "4444444444"),
        User(userId = "U5", name = "Pranav", email = "pranav@example.com", mobileNo = "5555555555")
    )
    val splitService = SplitService()
    println("Enter amount to split")
    val amount = readln().toBigDecimal()

    println("Enter user name between them you want to split, make sure first user is payee")
    val listOfUser = readln()
        .split(",")
        .toList()

    val userToSplit: MutableList<User> = mutableListOf()
    for (userName in allUsers) {
        if(userName.name in listOfUser){
            userToSplit.add(userName)
        }
    }

    splitService.splitAmount(amount, userToSplit.first(), userToSplit)
}