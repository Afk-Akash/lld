package src.repository

import src.model.DataFactory
import src.model.Driver
import src.model.Location
import java.time.LocalDateTime

class DriverRepository {
    val location = Location(
        123456789,
        987654321,
        LocalDateTime.now()
    )
    val driver1 = DataFactory.getDriver(
        id = "1",
        location = location
        )
    val driver2 = DataFactory.getDriver(
        id = "2",
        location = location
        )
    val driverCollection = mutableMapOf("1" to driver1, "2" to driver2)

    fun getDriver(driverId: String): Driver? {
        return driverCollection[driverId]
    }

    fun addDriver(driver: Driver) {
        driverCollection.put(driver.id, driver)
    }
}