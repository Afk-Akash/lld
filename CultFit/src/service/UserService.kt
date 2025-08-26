package service

import entity.User
import repository.UserRepository

class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): MutableList<User> {
        return userRepository.getAllUsers()
    }

    fun getUserById(id: Long): User {
        return userRepository.getById(id)
    }

    fun addUser(user: User) {
        userRepository.addUser(user)
    }

    fun getUserUsingNameAndPassword(userName: String, password: String): User {
        return userRepository.findByUserNameAndPassword(userName, password)
    }

}