package org.bookMyShow.controller

import org.bookMyShow.enums.City
import org.bookMyShow.model.Movie
import org.bookMyShow.model.MovieShow
import org.bookMyShow.model.Theater

class TheaterController(
    private var allTheater: MutableSet<Theater>
) {

    fun getAllShowOfMovieWithInTheCity(movie: Movie, city: City): Map<Theater, List<MovieShow>> {
        val movieShow: MutableMap<Theater, List<MovieShow>> = mutableMapOf()

        allTheater.forEach { theater: Theater ->
            if(theater.city == city){
                val allShow = theater.movieShows
                val givenMovieShow: MutableList<MovieShow> = mutableListOf()
                allShow.forEach { movieShow ->
                    if (movieShow.movie.id == movie.id) {
                        givenMovieShow.add(movieShow)
                    }
                }

                if (givenMovieShow.size > 0) {
                    movieShow[theater] = givenMovieShow
                }
            }
        }
        return movieShow
    }

}