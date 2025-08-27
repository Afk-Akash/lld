package data

import entity.CultClass
import entity.Location
import entity.ScheduledCultClass
import entity.User
import enums.ClassStatus
import enums.ClassType
import enums.MembershipType
import java.time.LocalDate
import java.time.LocalDateTime

object StaticData {

    val admin = User(
        userId = 1,
        name = "admin",
        password = "admin",
        membershipType = MembershipType.PLATINUM,
        membershipExpiryDate = LocalDate.now().plusDays(30),
        isAdmin = true
    )

    val scheduledCultClassGym = ScheduledCultClass(
        scheduledCultClassId = 12345,
        cultClass = CultClass(
            classId = 1,
            classType = ClassType.GYM
        ),
        startTime = LocalDateTime.now().plusHours(3),
        endTime = LocalDateTime.now().plusHours(4),
        classStatus = ClassStatus.SCHEDULED,
        capacity = 50,
        confirmedUsersCount = 0,
        location = Location(
            address = "Indira Nagar",
            city = "Bengaluru",
            pinCode = 841234
        ),
        confirmedUser = mutableListOf(),
        waitlistUser = ArrayDeque()
    )
    val scheduledCultClassYoga = ScheduledCultClass(
        scheduledCultClassId = 123456,
        cultClass = CultClass(
            classId = 2,
            classType = ClassType.YOGA
        ),
        startTime = LocalDateTime.now().plusHours(8),
        endTime = LocalDateTime.now().plusHours(10),
        classStatus = ClassStatus.SCHEDULED,
        capacity = 50,
        confirmedUsersCount = 0,
        location = Location(
            address = "Indira Nagar",
            city = "Bengaluru",
            pinCode = 841234
        ),
        confirmedUser = mutableListOf(),
        waitlistUser = ArrayDeque()
    )
    val scheduledCultClassZoomba = ScheduledCultClass(
        scheduledCultClassId = 123457,
        cultClass = CultClass(
            classId = 3,
            classType = ClassType.ZOOMBA
        ),
        startTime = LocalDateTime.now().plusMinutes(25),
        endTime = LocalDateTime.now().plusHours(1),
        classStatus = ClassStatus.SCHEDULED,
        capacity = 1,
        confirmedUsersCount = 0,
        location = Location(
            address = "Indira Nagar",
            city = "Bengaluru",
            pinCode = 841234
        ),
        confirmedUser = mutableListOf(),
        waitlistUser = ArrayDeque()
    )

    fun updateCultClass(
        cultClass: CultClass,
        startTime: LocalDateTime,
        location: Location
    ): ScheduledCultClass {
        return scheduledCultClassGym.copy(
            cultClass =cultClass,
            startTime = startTime,
            location = location
        )
    }

    val scheduledCultClasses = mutableListOf(
        scheduledCultClassGym,scheduledCultClassYoga,scheduledCultClassZoomba
    )
}