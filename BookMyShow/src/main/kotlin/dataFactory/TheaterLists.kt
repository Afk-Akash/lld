package org.bookMyShow.dataFactory

import org.bookMyShow.enums.City
import org.bookMyShow.enums.SeatCategory
import org.bookMyShow.enums.Language
import org.bookMyShow.model.Movie
import org.bookMyShow.model.MovieShow
import org.bookMyShow.model.Screen
import org.bookMyShow.model.Theater
import java.time.LocalTime

val intersteller = Movie(id = "M3", movieName = "Interstellar", genre = "Sci-Fi")
val dangal = Movie(id = "M4", movieName = "Dangal", genre = "Action Drama")
val threeIdiots = Movie(id = "M2", movieName = "3 Idiots", genre = "Drama")
val rrr = Movie(id = "M7", movieName = "RRR", genre = "Action")
val mumbaiMovies = listOf(
    intersteller,
    dangal
)

val bangaloreMovies = listOf(
    intersteller,
    threeIdiots
)

val chennaiMovies = listOf(
    rrr,
    dangal
)

val lucknowMovies = listOf(
    dangal,
    threeIdiots
)

val commonMovies = listOf(
    intersteller,
    rrr
)

private val defaultScreens3 = listOf(
    Screen(
        id = "screen-1",
        seatCount = mapOf(
            SeatCategory.VIP to 10,
            SeatCategory.PREMIUM to 20,
            SeatCategory.REGULAR to 70
        )
    ),
    Screen(
        id = "screen-2",
        seatCount = mapOf(
            SeatCategory.VIP to 20,
            SeatCategory.PREMIUM to 20,
            SeatCategory.REGULAR to 60
        )
    ),
    Screen(
        id = "screen-3",
        seatCount = mapOf(
            SeatCategory.VIP to 0,
            SeatCategory.PREMIUM to 30,
            SeatCategory.REGULAR to 70
        )
    )
)

private val defaultScreens2 = listOf(
    Screen(
        id = "screen-1",
        seatCount = mapOf(
            SeatCategory.VIP to 10,
            SeatCategory.PREMIUM to 20,
            SeatCategory.REGULAR to 70
        )
    ),
    Screen(
        id = "screen-2",
        seatCount = mapOf(
            SeatCategory.VIP to 20,
            SeatCategory.PREMIUM to 20,
            SeatCategory.REGULAR to 60
        )
    )
)

val theatersSet: MutableSet<Theater> = mutableSetOf(
    Theater(
        id = "T1",
        theaterName = "INOX Parel",
        city = City.MUMBAI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = mumbaiMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = mumbaiMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = mumbaiMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),
    Theater(
        id = "T2",
        theaterName = "PVR BKC",
        city = City.MUMBAI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS4",
                screen = defaultScreens3[2],
                movie = mumbaiMovies[1],
                startTime = LocalTime.of(11, 15),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS5",
                screen = defaultScreens3[2],
                movie = mumbaiMovies[0],
                startTime = LocalTime.of(19, 0),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T3",
        theaterName = "Cinepolis Vashi",
        city = City.MUMBAI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = mumbaiMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = mumbaiMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = mumbaiMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T4",
        theaterName = "PVR MG Road",
        city = City.BANGALORE,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = bangaloreMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = bangaloreMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = bangaloreMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T5",
        theaterName = "PVR E-City",
        city = City.BANGALORE,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = bangaloreMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = bangaloreMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = bangaloreMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T6",
        theaterName = "INOX Velacherry",
        city = City.CHENNAI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = chennaiMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = chennaiMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = chennaiMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T7",
        theaterName = "INOX Adyar",
        city = City.CHENNAI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = chennaiMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = chennaiMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = chennaiMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T8",
        theaterName = "Cinepolis OMR",
        city = City.CHENNAI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[0],
                movie = chennaiMovies[0],
                startTime = LocalTime.of(10, 0),
                language = Language.ENGLISH
            ),
            MovieShow(
                id = "MS2",
                screen = defaultScreens3[0],
                movie = chennaiMovies[1],
                startTime = LocalTime.of(13, 30),
                language = Language.HINDI
            ),
            MovieShow(
                id = "MS3",
                screen = defaultScreens3[1],
                movie = chennaiMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),
    Theater(
        id = "T9",
        theaterName = "PVR Tarwara more",
        city = City.SIWAN,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[1],
                movie = commonMovies[1],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T10",
        theaterName = "Cinepolis HauzKhas",
        city = City.NEW_DELHI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[1],
                movie = commonMovies[0],
                startTime = LocalTime.of(16, 45),
                language = Language.ENGLISH
            )
        )
    ),

    Theater(
        id = "T11",
        theaterName = "PVR Sarojini Nagar",
        city = City.NEW_DELHI,
        screens = defaultScreens3,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[1],
                movie = commonMovies[1],
                startTime = LocalTime.of(16, 45),
                language = Language.HINDI
            )
        )
    ),

    Theater(
        id = "T12",
        theaterName = "Cinepolis Aiashbagh",
        city = City.LUCKNOW,
        screens = defaultScreens2,
        movieShows = listOf(
            MovieShow(
                id = "MS1",
                screen = defaultScreens3[1],
                movie = commonMovies[1],
                startTime = LocalTime.of(16, 45),
                language = Language.HINDI
            )
        )
    )
)
