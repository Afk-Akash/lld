package org.bookMyShow.service

import org.bookMyShow.model.MovieShow
import org.bookMyShow.model.ShowSeat

object ShowSeatService: ShowSeatInterface {
    private val seatStorage: MutableMap<String, List<ShowSeat>> = mutableMapOf()

    fun init(allShow: List<MovieShow>) {
        allShow.forEach { show ->
            seatStorage[show.id] = generateShowSeats(show)
        }
    }


    private fun generateShowSeats(movieShow: MovieShow): List<ShowSeat> {
        return movieShow.screen.seats
            .map { seat ->  ShowSeat(seat = seat, movieShowId = movieShow.id) }
    }
    override fun getSeatAvailability(movieShowId: String): List<ShowSeat> {
        return seatStorage[movieShowId]?.filter { !it.isBooked } ?: emptyList()
    }

    override fun bookSeat(movieShowId: String, seats: List<ShowSeat>): Boolean {
        seats.forEach { requestedSeat ->
            seatStorage[movieShowId]?.forEach {
                if(it == requestedSeat && it.isBooked){
                    println(it)
                    throw RuntimeException("This seat is already booked $requestedSeat")
                }
                if (it == requestedSeat){
                    it.isBooked = true
                }
            }
        }
        return true
    }

}