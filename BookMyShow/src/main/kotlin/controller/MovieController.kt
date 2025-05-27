package org.bookMyShow.controller

import org.bookMyShow.enums.City
import org.bookMyShow.model.Movie

class MovieController(
    private var cityVsMovies: MutableMap<City, List<Movie>>,
) {
    private var allMovies: MutableSet<Movie> = mutableSetOf()

    init {
        cityVsMovies.forEach { (_, movieList) ->
            movieList.forEach { movie ->
                allMovies.add(movie)
            }
        }
    }

    fun getAllMovies(): List<String> {
        val movieSet = mutableSetOf<String>()
        cityVsMovies.forEach { (_, movieList) ->
            movieList.forEach { movieName ->
                movieSet.add(movieName.movieName)
            }
        }
        return movieSet.toList()
    }

    fun getMoviesByCity(city: City): List<String> {
        val movieSet = mutableSetOf<String>()
        cityVsMovies.forEach { (localCity, movieList) ->
            movieList.forEach { movie ->
                if(localCity == city){
                    movieSet.add(movie.movieName)
                }
            }
        }
        return movieSet.toList()
    }

    fun getMoviesByName(movieName: String): Movie? {
        return allMovies.find { movieName == it.movieName }
    }
}