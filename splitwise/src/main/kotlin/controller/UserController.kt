package org.splitwise.controller

import org.splitwise.model.User
import java.util.UUID

class UserController {
    private val userStorage: MutableList<User> = mutableListOf()

    fun createUser(name: String, email: String?, mobileNo: String?): User?{
        if(email == null && mobileNo == null){
            println("Either email or mobile no should be present")
            return null
        }
        val user = User(
            userId = UUID.randomUUID().toString(),
            name = name,
            email = email,
            mobileNo = mobileNo
        )

        userStorage.add(user)
        println("User was created successfully with id ${user.userId}")
        return user
    }

    fun createUser(user: User): User{
        userStorage.add(user)
        println("User was created successfully with id ${user.userId}")
        return user
    }

    fun getUser(id: String): User?{
        userStorage.forEach { user ->
            if (user.userId == id) return user
        }
        return null
    }

    fun showAllUser(): List<User> {
        return userStorage
    }
}