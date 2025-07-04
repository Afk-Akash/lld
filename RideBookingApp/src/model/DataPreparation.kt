package src.model

object DataFactory {
    fun getDriver(
        id: String,
        name: String = "default name",
        mobile: String = "9876543210",
        location: Location ,
        vrn: String = "dummy vrn"
    ) =
        Driver(
            id = id,
            name = name,
            mobile = mobile,
            location = location,
            vehicleNo = vrn
        )

    fun getRider(
        id: String,
        name: String = "default name",
        mobile: String = "9876543210",
    ) =
        Rider(
            id = id,
            name = name,
            mobile = mobile,
        )
}