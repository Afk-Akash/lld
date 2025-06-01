package org.bookMyShow

import org.bookMyShow.controller.MovieController
import org.bookMyShow.controller.TheaterController
import org.bookMyShow.dataFactory.*
import org.bookMyShow.enums.City
import org.bookMyShow.model.*
import org.bookMyShow.service.BookingService
import org.bookMyShow.service.ShowSeatService

fun main() {
    val cityVsMovie: MutableMap<City, List<Movie>> = mutableMapOf(
        City.BANGALORE to bangaloreMovies,
        City.MUMBAI to mumbaiMovies,
        City.LUCKNOW to lucknowMovies,
        City.CHENNAI to chennaiMovies,
        City.SIWAN to commonMovies,
        City.LUCKNOW to commonMovies
    )


    val movieController = MovieController(cityVsMovie)
    val theaterController = TheaterController(theatersSet)

    val allShows = theatersSet
        .flatMap { it.movieShows }
        .distinctBy { it.id }

    ShowSeatService.init(allShows)

    val bookingService = BookingService(
        movieController = movieController,
        theaterController = theaterController,
        showSeatBookingService = ShowSeatService
    )

    while(true){
        bookingService.startBooking()
        println("Press 1 to close the application...")
        val input = readln()
        if(input == "1") break
    }
}