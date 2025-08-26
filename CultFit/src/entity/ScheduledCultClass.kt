package entity

import enums.ClassStatus
import java.time.LocalDateTime

data class ScheduledCultClass(
    val scheduledCultClassId: Long,
    val cultClass: CultClass,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val classStatus: ClassStatus,
    val capacity: Int,
    var confirmedUsersCount: Int,
    val location: Location,
    var confirmedUser: MutableList<User> = mutableListOf(),
    var waitlistUser: ArrayDeque<User> = ArrayDeque()
) {
    fun isFull(): Boolean {
        return confirmedUsersCount == capacity
    }
}
