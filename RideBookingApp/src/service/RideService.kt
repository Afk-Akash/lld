package src.service

import src.model.Ride
import src.model.RideRequest
import src.model.enum.DriverStatus
import src.model.enum.RideRequestStatus
import src.model.enum.RideStatus
import src.repository.DriverRepository
import src.repository.RideRepository
import src.repository.RideRequestRepository
import src.strategy.driverMatchingStrategy.DriverMatchingStrategy
import java.time.LocalDateTime

class RideService(
    val driverMatchingStrategy: DriverMatchingStrategy,
    val notificationService: NotificationService,
    val driverRepository: DriverRepository,
    val rideRepository: RideRepository,
    val rideRequestRepository: RideRequestRepository,
    val paymentService: PaymentService
) {

    fun cancelRideRequest(rideRequest: RideRequest) {
        val driverId = rideRequest.driverId
        if(driverId != null){
            val driver = driverRepository.getDriver(driverId)
            if(driver == null){
                println("no Driver found with id $driverId")
                return
            }
            notificationService.notifyDriver(driver)
        }
        rideRequest.status = RideRequestStatus.CANCELLED
        rideRequestRepository.saveRideRequest(rideRequest)
    }

    fun rideRequest(rideRequest: RideRequest){
        rideRequestRepository.saveRideRequest(rideRequest)
        val availableDrivers = driverMatchingStrategy.findDrivers(rideRequest)

        notificationService.notifyDrivers(availableDrivers)
    }

    fun endRide(ride: Ride){
        val currentTime = LocalDateTime.now()

        ride.rideEndTime = currentTime
        ride.status = RideStatus.COMPLETED

        rideRepository.saveRide(ride)
        val driver = driverRepository.getDriver(ride.driverId)!!

        driver.status = DriverStatus.AVAILABLE

        paymentService.initiatePayment(ride.fare, 1)
    }
}
