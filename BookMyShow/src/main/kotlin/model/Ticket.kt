package org.bookMyShow.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Ticket(
    val ticketId: String,
    val movieShow: MovieShow,
    val seats: List<ShowSeat>,
    val amount: BigDecimal,
    val bookingTime: LocalDateTime
)
