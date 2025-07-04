package src.service

import src.model.Driver
import src.model.Ride
import src.model.RideRequest
import src.model.enum.RideRequestStatus
import src.model.enum.RideStatus
import src.repository.RideRepository
import src.repository.RideRequestRepository
import java.time.LocalDateTime

class DriverHandler(
    val notificationService: NotificationService,
    val rideRequestRepository: RideRequestRepository,
    val rideRepository: RideRepository,
    val rideService: RideService
) {


    fun acceptRide(rideRequest: RideRequest, driver: Driver) {
        //pre validation
        if(!rideRequest.driverId.isNullOrBlank()) {
            println("This ride has already been accepted by someone else")
            return
        }
        rideRequest.driverId = driver.id

        notificationService.notifyRider(rideRequest)
    }

    fun cancelRide(rideRequest: RideRequest, driver: Driver) {
        if(rideRequest.driverId != driver.id){
            println("this ride is not associated with the driver")
            return
        }
        rideRequest.driverId = null
        notificationService.notifyRider(rideRequest)

        rideRequest.status = RideRequestStatus.CANCELLED
        rideRequestRepository.saveRideRequest(rideRequest)
    }

    fun startRide(rideRequest: RideRequest, otp: Int) {
        //pre validation
        if (rideRequest.otp != otp) {
            println("otp is incorrect...")
            return
        }
        val currentTime = LocalDateTime.now()
        val ride = Ride.fromRideRequest(rideRequest, currentTime)
        rideRepository.saveRide(ride)
    }

    fun endRide(ride: Ride){
        //pre validation
        val currentTime = LocalDateTime.now()

        ride.rideEndTime = currentTime
        ride.status = RideStatus.COMPLETED

        rideService.endRide(ride)
    }
}