package service

import entity.User
import enums.ClassType.Companion.getClassType
import enums.MembershipType.Companion.getMembership
import java.time.LocalDate
import kotlin.random.Random

class CoreCultFitService(
    private val bookingService: BookingService,
    private val userService: UserService,
    private val cultClassService: CultClassService
) {

    fun registerUser() {
        println("Enter your UserName under 20 characters")
        val name = readln()
        println("Enter your Password under 20 characters")
        val password = readln()

        println("Select Membership Type")
        val memberShipType = getMembership(readln().lowercase())
        if(memberShipType == null){
            println("invalid membership type")
            return
        }

        val id = Random.nextLong(1, Long.MAX_VALUE)

        val user = User(
            userId = id,
            name = name,
            password = password,
            membershipType = memberShipType,
            membershipExpiryDate = LocalDate.now().plusDays(30),
            isAdmin = false
        )

        userService.addUser(user)
    }

    fun loginUser(): User {
        println("Enter your UserName under 20 characters")
        val name = readln()
        println("Enter your Password under 20 characters")
        val password = readln()

        val user = userService.getUserUsingNameAndPassword(name, password)
        return user
    }

    fun init() {
        println("1. Register a new user")
        println("2. login")
        val input = readln().toInt()
        if(input == 1){
            registerUser()
        }else{
            val user = loginUser()
            showAllTheOptions(user)
        }
        init()
    }

    private fun showAllTheOptions(user: User) {
        println("1. show all the classes")
        println("2. show my booking of today")
        println("3. Cancel a booking")
        println("4. Logout")
        val input = readln().toInt()
        if(input == 4){
            println("Logged out")
            return
        }else if(input == 1){
            handleClassBooking(user)
        }else if(input == 2){
            bookingService.showAllUserBookings(user.userId)
        }else if(input == 3){
            println("Select class to cancel")
            bookingService.cancelBooking(user.userId)
        }
        showAllTheOptions(user)
    }

    private fun handleClassBooking(user: User) {
        println("Enter the class type - 1. GYM, 2. YOGA, 3. ZOOMBA")
        val input = readln().toInt()
        val classType = getClassType(input)
        bookingService.bookClassByClassType(user, classType)
    }
}