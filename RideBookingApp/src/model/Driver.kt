package src.model

import src.model.enum.DriverStatus

data class Driver(
    val id: String,
    val name: String,
    val mobile: String,
    val location: Location,
    val vehicleNo: String,
    var status: DriverStatus = DriverStatus.AVAILABLE
)
