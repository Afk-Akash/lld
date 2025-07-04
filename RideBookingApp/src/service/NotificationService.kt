package src.service

import src.model.Driver
import src.model.RideRequest
import src.model.Rider
import src.strategy.notifications.NotificationFactoryBuilder

class NotificationService(
    val notificationType: Int,
    val notificationFactoryBuilder: NotificationFactoryBuilder
) {
    private val notificationStrategy = notificationFactoryBuilder.getNotificationStrategy(notificationType)


    fun notifyDrivers(drivers: List<Driver>) {
        notificationStrategy.notifyDrivers(drivers)
    }


    fun notifyDriver(driver: Driver) {
        notificationStrategy.notifyDriver(driver)
    }


    fun notifyRider(rideRequest: RideRequest) {
        notificationStrategy.notifyRider(rideRequest)
    }
}