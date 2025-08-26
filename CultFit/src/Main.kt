import entity.User
import repository.CultClassRepository
import repository.UserBookingRepository
import repository.UserRepository
import service.BookingService
import service.CoreCultFitService
import service.CultClassService
import service.UserService

fun main() {

    val userBookingRepository = UserBookingRepository()
    val cultClassRepository = CultClassRepository()
    val userRepository = UserRepository()

    val cultClassService = CultClassService(
        cultClassRepository = cultClassRepository,
        userBookingRepository = userBookingRepository
    )

    val userService = UserService(
        userRepository = userRepository
    )

    val bookingService = BookingService(
        cultClassService = cultClassService,
        userBookingRepository = userBookingRepository,
        userService = userService
    )


    val coreCultFitService = CoreCultFitService(
        bookingService = bookingService,
        userService = userService,
        cultClassService = cultClassService
    )

    coreCultFitService.init()
}