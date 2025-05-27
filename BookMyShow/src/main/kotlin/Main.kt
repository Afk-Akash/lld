package org.bookMyShow

import org.bookMyShow.controller.MovieController
import org.bookMyShow.controller.TheaterController
import org.bookMyShow.dataFactory.*
import org.bookMyShow.enums.City
import org.bookMyShow.model.*
import org.bookMyShow.service.BookingService

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

    val bookingService = BookingService(
        movieController = movieController,
        theaterController = theaterController
    )

    bookingService.startBooking()
}