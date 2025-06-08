package org.splitwise.model

data class Group(
    val groupId: String,
    val groupName: String,
    val users: MutableSet<User>,
    val expenses: MutableList<Expense> = mutableListOf()
)
