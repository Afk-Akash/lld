Users should be able to:
Register themselves
View upcoming events
When a new event is added, notify users who are interested in the event category (e.g., Tech, Music, etc.)



User {
id
name,
phone,
}



EventCategory{
id,
categoryName,}


EventCategory.id many relation user.id


eventUserMap
_id, eventId , userId
        Id1, u1
        Id1, u2

select UserId from eventUserMap join Event on Event.EventCategoryId = eventUserMap.eventId where Event.name  is 'tech';


Event(
id,
name,
place,
timeStamp,
EventCategory.id
)

-> /create -> Post -> enterPhone, name -> generateId -> Store UserTable

-> /ViewEvents/{id} -> Get -> get all the events from Event table

-> 

user can subscribe to the category

user will get notification when new message in this category

interface notificationStrategy -> notifyUsers(List<Users>)

@Component
PushNotification(smsNotification: SmsNotification) -> notifyUsers(List<Users>){/push notification}


@Component
SmsNotification() -> notifyUsers(List<Users>){/sms notification}


factoryBuilder(smsNotification, pushNotification)  -> getNotificationStrategy(type) -> notificationStrategy.notifyUsers()
getNotificationStrategy(type) :notificationStrategy {

if(type == 1) smsNotification
else PushNotification
    
}

Component annotation -> signleton

EventService(val notificationStrategy : NotificationStrategy, ) -> type from config ->factoryBuilder.getNotificationStrategy(type) -> 
if(type == 1) 

EventController(val notificationStrategy : NotificationStrategy ) {
EventService(notificationStrategy)
}




