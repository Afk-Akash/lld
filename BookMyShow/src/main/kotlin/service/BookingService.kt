package org.bookMyShow.service

import org.bookMyShow.controller.MovieController
import org.bookMyShow.controller.PaymentController
import org.bookMyShow.controller.TheaterController
import org.bookMyShow.enums.City
import org.bookMyShow.model.Movie
import org.bookMyShow.model.MovieShow
import org.bookMyShow.model.ShowSeat
import org.bookMyShow.model.Theater
import org.bookMyShow.strategy.CcAvenuePayment
import org.bookMyShow.strategy.UpiPayment
import java.math.BigDecimal

class BookingService(
    private val movieController: MovieController,
    private val theaterController: TheaterController,
    private val showSeatBookingService: ShowSeatInterface
) {

    fun startBooking() {

        val selectedCity = selectCity() ?: return

        val selectedMovie = selectMovie(selectedCity) ?: return


        val shows = selectTheaterAndShow(selectedCity, selectedMovie) ?: return

        shows.forEachIndexed { index, show ->
            println("${index + 1}. ${show.startTime} - ${show.language} (${show.screen.id})")
        }

        print("Enter the show number: ")
        val showIndex = readlnOrNull()?.toIntOrNull()

        if (showIndex == null || showIndex !in 1..shows.size) {
            println("Invalid input. Please restart and choose a valid show.")
            return
        }

        val selectedShow = shows[showIndex - 1]

        println("You selected the show at ${selectedShow.startTime} on screen ${selectedShow.screen.id}.")
        seatBooking(selectedShow)
    }

    private fun seatBooking(selectedShow: MovieShow) {
        println("checking for seat availability")

        val availableSeat = showSeatBookingService.getSeatAvailability(selectedShow.id)
        if (availableSeat.isEmpty()) {
            println("Sorry, no seats left for this show.")
            return
        }

        availableSeat.forEachIndexed { index, showSeat ->
            println("${index + 1}. ${showSeat.seat.seatNo} - ${showSeat.seat.seatCategory}")
        }

        print("Enter seat numbers (comma-sep): ")
        val selections = readlnOrNull()
            ?.split(",")
            ?.map { it.trim().uppercase() }
            .orEmpty()
            .filter { num ->
                availableSeat.any { it.seat.seatNo.equals(num, ignoreCase = true) }
            }

        val seatsToBook = availableSeat.filter { showSeat ->
            showSeat.seat.seatNo in selections
        }
        if (selections.isEmpty()) {
            println("No valid seats chosen."); return
        }

        val amount = seatsToBook.sumOf { it.seat.seatCategory.amount }

        performTransaction(amount)

        showSeatBookingService.bookSeat(movieShowId = selectedShow.id, seatsToBook)

        println("Ticket booked")
    }

    private fun performTransaction(amount: BigDecimal) {
        print("Pay by: 1)CCAvenue  2)UPI: ")
        val strategy = when (readlnOrNull()) {
            "1" -> CcAvenuePayment()
            "2" -> UpiPayment()
            else -> return println("Cancelled")
        }

        val paymentController = PaymentController(strategy)
        if (!paymentController.executePayment(amount)) {
            println("Payment failed")
            return
        }
    }

    private fun selectCity(): City? {
        println("Please choose your city:")
        City.entries.forEachIndexed { index, city ->
            println("${index + 1}. ${city.name}")
        }

        print("Enter the city number: ")
        val cityIndexInput = readlnOrNull()?.toIntOrNull()

        if (cityIndexInput == null || cityIndexInput !in 1..City.entries.size) {
            println("Invalid input. Please restart and choose a valid city number.")
            return null
        }

        val selectedCity = City.entries[cityIndexInput - 1]
        println("You selected: ${selectedCity.name}")
        return selectedCity
    }

    private fun selectMovie(selectedCity: City): Movie? {
        println("Fetching movies available in ${selectedCity.name}...")
        val movies = movieController.getMoviesByCity(selectedCity)

        if (movies.isEmpty()) {
            println("No movies available in ${selectedCity.name}")
            println("Please try again")
            return null
        } else {
            println("Available movies:")
            movies.forEachIndexed { index, movie ->
                println("${index + 1}. $movie")
            }
        }
        print("Enter the movie number: ")
        val movieIndexInput = readlnOrNull()?.toIntOrNull()

        if (movieIndexInput == null || movieIndexInput !in 1..movies.size) {
            println("Invalid input. Please restart and choose a valid movie number.")
            return null
        }
        val selectedMovieName = movies[movieIndexInput - 1]
        val selectedMovie = movieController.getMoviesByName(selectedMovieName)
        return selectedMovie
    }

    private fun selectTheaterAndShow(selectedCity: City, selectedMovie: Movie): List<MovieShow>? {
        val allShows = theaterController.getAllShowOfMovieWithInTheCity(selectedMovie, selectedCity)

        println("Available theaters and shows for '${selectedMovie.movieName}' in ${selectedCity.name}:")
        val theaters = allShows.keys.toList()

        theaters.forEachIndexed { index, theater ->
            println("${index + 1}. ${theater.theaterName}")
        }

        print("Enter the theater number: ")
        val theaterIndex = readlnOrNull()?.toIntOrNull()

        if (theaterIndex == null || theaterIndex !in 1..theaters.size) {
            println("Invalid input. Please restart and choose a valid theater number.")
            return null
        }

        val selectedTheater = theaters[theaterIndex - 1]
        println("Available shows in ${selectedTheater.theaterName}:")

        return allShows[selectedTheater]
    }
}