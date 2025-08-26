package repository

import entity.UserBooking

class UserBookingRepository {
    var userBookingList = mutableListOf<UserBooking>()

    fun getBookingByUserId(userId: Long): List<UserBooking> {
        return userBookingList.filter { userBooking ->
            userBooking.userId == userId
        }
    }

    fun cancelBookingByUserIdAndCultClassId(booking: UserBooking): Boolean {
        return userBookingList.remove(booking)
    }


    fun addBooking(booking: UserBooking) {
        userBookingList.add(booking)
    }
}