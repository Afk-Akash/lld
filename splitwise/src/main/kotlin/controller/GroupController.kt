package org.splitwise.controller

import org.splitwise.model.BalanceSheet
import org.splitwise.model.Group
import org.splitwise.model.User
import java.util.UUID

class GroupController(
    val balanceSheet: BalanceSheet
) {
    private val groupStorage: MutableMap<String, Group> = mutableMapOf()


    fun createGroup(users: List<User>, groupName: String) {
        val groupId = UUID.randomUUID().toString()
        val newGroup = Group(
            groupId = groupId,
            groupName = groupName,
            users = users.toMutableSet()
        )
        groupStorage[groupId] = newGroup
        println("groupId ${newGroup.groupId}")
        balanceSheet.initGroupBalance(newGroup)
    }

    fun addUserInGroup(groupId: String, user: User) {
        val group = groupStorage[groupId]
        if(group != null) {
            group.users.add(user)
            println("User ${user.name} was added to group ${group.groupName}")
        }else{
            println("Group with this id was not found")
        }
    }

    fun removeUserFromGroup(groupId: String, user: User) {
        val group = groupStorage[groupId]
        if (group != null) {
            group.users.removeIf { it == user }
            println("User ${user.name} was removed from group ${group.groupName}")
        } else {
            println("Group was not found with group Id")
        }
    }

    fun getGroupMembers(groupId: String): List<User> {
        return groupStorage[groupId]!!.users.toMutableList()
    }

    fun getGroup(groupId: String): Group? {
        return groupStorage[groupId]
    }
}