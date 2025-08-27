package service

import entity.User
import entity.UserBooking
import enums.ClassStatus
import enums.ClassType
import repository.UserBookingRepository
import java.time.LocalDateTime

class BookingService(
    val cultClassService: CultClassService,
    private val userBookingRepository: UserBookingRepository,
    private val userService: UserService
) {

    fun bookClassByClassType(user: User, classType: ClassType) {
        val classes = cultClassService.fetchAllClasses()
        val filteredClasses = classes.filter { cultClass ->
            cultClass.classStatus == ClassStatus.SCHEDULED &&
                    cultClass.cultClass.classType == classType
        }
        var x = 1
        for(cultClass in filteredClasses) {
            println("$x - Location ${cultClass.location} at Start Time ${cultClass.startTime} and End Time ${cultClass.endTime}")
            x++
        }
        val userInputClass = readln().toInt()
        if(userInputClass in x..<0){
            println("Invalid input")
            return
        }
        val classToBook = filteredClasses[userInputClass-1]

        cultClassService.bookCultClassForUser(user, classToBook.scheduledCultClassId)
    }

    fun showAllUserBookings(userId: Long) {
       val bookings = userBookingRepository.getBookingByUserId(userId)
        var x = 1
        bookings.forEach { booking ->
            val cultClass = cultClassService.fetchCultClassUsingId(booking.cultClassId)
            println("$x - Class ${cultClass.cultClass.classType} with Start Time ${cultClass.startTime} is ${cultClass.classStatus}")
            x++
        }
    }

    fun cancelBooking(userId: Long) {
        val bookings = userBookingRepository.getBookingByUserId(userId)
        var x = 1
        bookings.forEach { booking ->
            val cultClass = cultClassService.fetchCultClassUsingId(booking.cultClassId)
            println("$x - Class ${cultClass.cultClass.classType} with Start Time ${cultClass.startTime} is ${cultClass.classStatus}")
            x++
        }
        println("Select a class to cancel")
        val input = readln().toInt()
        val classToCancel = cultClassService.fetchCultClassUsingId(bookings[input-1].cultClassId)
        if(classToCancel.startTime.plusMinutes(30) <= LocalDateTime.now()){
            throw IllegalArgumentException("You can only cancel a class before 30 min of start")
        }
        val userBookingToCancel = UserBooking(
            userId = userId,
            cultClassId = classToCancel.scheduledCultClassId
        )
        userBookingRepository.cancelBookingByUserIdAndCultClassId(userBookingToCancel)
        val user = userService.getUserById(userId)

        classToCancel.confirmedUser.remove(user)
        classToCancel.confirmedUsersCount -= 1

        val waitlistUser = classToCancel.waitlistUser.removeFirstOrNull()
        if(waitlistUser == null){
            return
        }else{
            cultClassService.bookCultClassForUser(waitlistUser, classToCancel.scheduledCultClassId)
        }

    }
}