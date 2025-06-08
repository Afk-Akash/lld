package org.splitwise.model

import org.splitwise.controller.UserController
import java.math.BigDecimal

// oracle blackrock dmi finance cashFree, zepto, angelOne, mmt, Tekion, hdfc securities

class BalanceSheet {
    private val globalBalanceMap: MutableMap<User, MutableMap<User, BigDecimal>> = mutableMapOf()
    private val groupBalanceMap: MutableMap<String, MutableMap<User, MutableMap<User, BigDecimal>>> = mutableMapOf()

    fun settleAmount(settler: User, toWhom: User, amount: BigDecimal, groupId: String) {
        val groupBalance = groupBalanceMap[groupId]
        if (groupBalance == null) {
            println("group not found with id $groupId")
            return
        }

        val owedMap = groupBalance[toWhom]


        val currentDue = owedMap!![settler]

        if (currentDue != null) {
            owedMap[settler] = currentDue - amount
        }

        if (globalBalanceMap[toWhom]!![settler] != null) {
            globalBalanceMap[toWhom]!![settler] = globalBalanceMap[toWhom]!![settler]!!.minus(amount)
        }

        println("$settler settled successfully with $toWhom with $amount")
    }

    fun initGroupBalance(group: Group) {
        val groupId = group.groupId
        val users = group.users

        // Initialize for group
        groupBalanceMap[groupId] = mutableMapOf()

        for (payer in users) {
            groupBalanceMap[groupId]!![payer] = mutableMapOf()
            for (payee in users) {
                if (payer != payee) {
                    groupBalanceMap[groupId]!![payer]!![payee] = BigDecimal.ZERO
                }
            }
        }

        for (payer in users) {
            globalBalanceMap.putIfAbsent(payer, mutableMapOf())
            for (payee in users) {
                if (payer != payee) {
                    globalBalanceMap[payer]!!.putIfAbsent(payee, BigDecimal.ZERO)
                }
            }
        }
    }


    fun adjustAllUserBalanceInGroup(groupId: String) {
        val groupBalance = groupBalanceMap[groupId]
        if (groupBalance == null) {
            println("Group not found with this id")
            return
        }

        groupBalance.forEach { (payer, personsWhoOwe) ->
            personsWhoOwe.forEach { (person, amount) ->
                val personWhoOweBalanceMap = groupBalance[person]!!
                val amountPayerOwe = personWhoOweBalanceMap[payer] ?: BigDecimal.ZERO
                val finalAmount = amount - amountPayerOwe

                if (finalAmount > BigDecimal.ZERO) {
                    groupBalance[payer]!![person] = finalAmount
                    groupBalance[person]!![payer] = BigDecimal.ZERO
                } else if (finalAmount < BigDecimal.ZERO) {
                    groupBalance[person]!![payer] = -finalAmount
                    groupBalance[payer]!![person] = BigDecimal.ZERO
                }
            }
        }


        globalBalanceMap.forEach { (payer, personsWhoOwe)->
            personsWhoOwe.forEach { (personWhoOwe, amount) ->

                val reverseAmount = globalBalanceMap[personWhoOwe]?.get(payer) ?: BigDecimal.ZERO

                val finalAmount = amount - reverseAmount
                if(finalAmount < BigDecimal.ZERO){
                    globalBalanceMap[personWhoOwe]!![payer] = -finalAmount
                    globalBalanceMap[payer]!![personWhoOwe] = BigDecimal.ZERO
                }else {
                    globalBalanceMap[payer]!![personWhoOwe] = finalAmount
                    globalBalanceMap[personWhoOwe]!![payer] = BigDecimal.ZERO
                }
            }
        }
    }

    fun updateGlobalBalance(paidBy: User, paidFor: User, amount: BigDecimal) {
        globalBalanceMap[paidBy]!![paidFor] = globalBalanceMap[paidBy]!![paidFor]!! + amount
    }

    fun updateGroupBalance(paidBy: User, paidFor: User, amount: BigDecimal, groupId: String) {
        val group = groupBalanceMap.getOrPut(groupId) { mutableMapOf() }
        val owedMap = group[paidBy]
        if (owedMap == null) {
            println("owedMap not found with user ${paidBy.name}")
            return
        }
        owedMap[paidFor] =
            groupBalanceMap[groupId]!![paidBy]!![paidFor]!! + amount

        globalBalanceMap[paidBy]!![paidFor] = globalBalanceMap[paidBy]!![paidFor]!! + amount
    }


    fun showGlobalBalance() {
        globalBalanceMap.forEach { (paidBy, paidForMap) ->
            println("User: ${paidBy.name} is owed by -")
            paidForMap.forEach { (owedBy, amount) ->
                println("${owedBy.name} owes ₹$amount")
            }
        }
    }

    fun showGroupBalance(groupId: String) {
        val groupBalance = groupBalanceMap[groupId]
        if (groupBalance == null) {
            println("groupBalance is null ")
            return
        }
        groupBalance.forEach { (paidBy, paidForMap) ->
            println("User: ${paidBy.name} is owed by -")
            paidForMap.forEach { (owedBy, amount) ->
                println("${owedBy.name} owes ₹$amount")
            }
        }
    }

    fun showBalanceOfUser(user: User) {
        println("User: ${user.name} is owed by -")
        globalBalanceMap[user]!!.forEach { (owedBy, amount) ->
            println("${owedBy.name} owes ₹$amount")
        }
    }
}
