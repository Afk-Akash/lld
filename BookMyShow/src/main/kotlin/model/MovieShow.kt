package org.bookMyShow.model

import org.bookMyShow.enums.Language
import java.time.LocalTime

data class MovieShow(
    val id: String,
    val screen: Screen,
    val movie: Movie,
    val startTime: LocalTime,
    val language: Language
)
