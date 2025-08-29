Design a system that can send notifications to users via email,
SMS, and push notifications. Users should be able to set preferences for notification types


UserDefaultSelectionService(
notificationType: String -> could be null,
factoryBuilder: FactoryBuilder,
userRepository: UserRepository
){

    notificationService:NotificationService // late init


    findNotificationType(userId: String) {
        if(notificationType != null){
            map[userId] = notificationType
            userRepository.saveUserPrefrence(userId, notificationType)
            notificationService = NotificationService(notificationType, factoryBuilder)
        } else {
                userPastPrefrence = userRepository.getUserPrefrence(userId)
                notificationType =  userRepository ? userPastPrefrence : "SMS"
                notificationService = NotificationService(notificationType, factoryBuilder)
            }
        
        notificationService.sendNotifications(user)
    }

}




NotificationService(
notificationType: String
factoryBuilder: FactoryBuilder
){
    notificationStrategy : NotificationStrategy  = factoryBuilder.getNotificationStrategy(notificationType)

    sendNotifications(user: User) {
        notificationStrategy.notifyUser(user, message)
    }
    
}

FactoryBuilder(smsNotification: SmsNotification
emailNotification: EmailNotification
pushNotification: PushNotification) {

    getNotificationStrategy(notificationType) :NotificationStrategy {
        if(notificationType == "sms") notificationStrategy = SmsNotification
        else if(notificationType == "email")notificationStrategy = emailNotification
        else if(notificationType == "push")notificationStrategy = pushNotification
    }
}


interface NotificationStrategy() {
    notifyUser(user: User, message: String)
}

SmsNotification implements NotificationStrategy {
    notifyUser(user: User, message: String) {
//sms notification code
}
}

EmailNotification implements NotificationStrategy {
notifyUser(user: User, message: String) {
//email notification code
}
}


PushNotification implements NotificationStrategy {
notifyUser(user: User, message: String) {
//push notification code
}
}