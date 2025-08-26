package service

import entity.ScheduledCultClass
import entity.User
import entity.UserBooking
import repository.CultClassRepository
import repository.UserBookingRepository

class CultClassService(
    private val cultClassRepository: CultClassRepository,
    private val userBookingRepository: UserBookingRepository
) {

    fun fetchAllClasses(): List<ScheduledCultClass> {
        return cultClassRepository.getAllClasses()
    }

    fun fetchCultClassUsingId(id: Long): ScheduledCultClass {
        return cultClassRepository.fetchById(id)
    }
    fun scheduleNewCultClass(scheduledCultClass:ScheduledCultClass) {
        cultClassRepository.putNewCultClass(scheduledCultClass)
    }

    fun bookCultClassForUser(user: User, scheduleClassId : Long) {
        val isSuccessfullyJoined = cultClassRepository.subscribeClass(user,scheduleClassId)
        if(isSuccessfullyJoined){
            val userBooking = UserBooking(
                userId = user.userId,
                cultClassId = scheduleClassId
            )
            userBookingRepository.addBooking(userBooking)
            println("User - ${user.name} successfully joined the class with id $scheduleClassId")
        }else{
            println("User - ${user.name} successfully joined waitlist queue for class with id $scheduleClassId")
        }
    }
}