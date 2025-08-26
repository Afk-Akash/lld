package repository

import entity.User
import javax.naming.AuthenticationException

class UserRepository {
    var userList: MutableList<User> = mutableListOf()

    fun getAllUsers(): MutableList<User> {
        return userList
    }
    fun getById(id:Long): User{
        userList.forEach { user ->
            if(user.userId == id) return user
        }
        throw IllegalArgumentException("user not found with id $id")
    }

    fun addUser(user: User) {
        userList.add(user)
    }

    fun findByUserNameAndPassword(userName: String, password: String): User {
        userList.forEach { user ->
            if(user.name == userName && user.password == password) return user
        }
        throw AuthenticationException("username or password is wrong")
    }
}