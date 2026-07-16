package service;

import model.Ticket;
import model.Vehicle;

public class ParkingLot {
    Integer floorCount;
    Integer parkingSpotCount;
    String entryGate;
    String exitGate;


    ParkingLot(
            Integer floorCount,
            Integer parkingSpotCount,
            String entryGate,
            String exitGate
    ) {
        this.entryGate = entryGate;
        this.parkingSpotCount = parkingSpotCount;
        this.floorCount = floorCount;
        this.exitGate = exitGate;

    }

    String parkVehicle(Vehicle vehicle) {
        return "";
    }

    String unParkVehicle(Ticket ticket) {
        return "";
    }
}
