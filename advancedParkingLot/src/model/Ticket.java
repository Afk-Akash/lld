package model;


import java.time.LocalDateTime;

public class Ticket {
    String ticketId;
    LocalDateTime entryTime;
    String parkingSpotId;
    Float amount;
    String notes = "This ticket is valid for 8 hours, extra amount will be charged after that";
}
