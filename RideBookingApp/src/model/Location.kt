package src.model

import java.time.LocalDateTime
import java.time.LocalTime

data class Location(
    val longitude: Long,
    val latitude: Long,
    val timeStamp: LocalDateTime
)
