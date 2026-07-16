package service;

import enums.VehicleType;
import model.ParkingSpot;
import model.Ticket;
import model.Vehicle;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParkingLot {
    Integer floorCount;
    Integer parkingSpotCount;
    String entryGate;
    String exitGate;
    TreeMap<Integer, TreeSet<Integer>> availableSpots = new TreeMap<>();


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

        for(int i = 1; i <= floorCount; i++){
            TreeSet<Integer> spots = new TreeSet<>();
            for(int j = 1; j <= parkingSpotCount; j++){
                spots.add(j);
            }
            availableSpots.put(i, spots) ;
        }
    }

    String parkVehicle(Vehicle vehicle) {
        return "";

    }

    ParkingSpot findAvailableSpot(VehicleType vehicleType, Integer preferredFloorNo) {
        return new ParkingSpot(

        );
    }

    String unParkVehicle(Ticket ticket) {
        return "";
    }
}
