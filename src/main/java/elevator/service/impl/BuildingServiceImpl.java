package elevator.service.impl;

import static elevator.util.Constants.FIRST_FLOOR;
import static elevator.util.Constants.MAX_FLOORS_QUANTITY;
import static elevator.util.Constants.MAX_PASSENGERS_PER_FLOOR;
import static elevator.util.Constants.MIN_FLOORS_QUANTITY;
import static elevator.util.Constants.MIN_PASSENGERS_PER_FLOOR;
import static elevator.util.Randomizer.getRandomQuantity;

import elevator.model.Building;
import elevator.model.Passenger;
import elevator.service.BuildingService;
import elevator.service.PassengerService;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BuildingServiceImpl implements BuildingService {
    private final PassengerService passengerService = new PassengerServiceImpl();
    private final int floorsQuantity = getRandomQuantity(MIN_FLOORS_QUANTITY, MAX_FLOORS_QUANTITY);

    @Override
    public Building createBuilding() {
        return new Building(floorsQuantity, determinePassengersOnFloors());
    }

    private Map<Integer, List<Passenger>> determinePassengersOnFloors() {
        Map<Integer, List<Passenger>> passengersInBuilding = new TreeMap<>();
        for (int i = FIRST_FLOOR; i <= floorsQuantity; i++) {
            int passengersQuantityOnTheFloor = getRandomQuantity(MIN_PASSENGERS_PER_FLOOR,
                                                                MAX_PASSENGERS_PER_FLOOR);
            List<Passenger> passengersOnTheFloor = new LinkedList<>();
            if (passengersQuantityOnTheFloor > 0) {
                for (int j = 0; j < passengersQuantityOnTheFloor; j++) {
                    Passenger passenger = passengerService.createPassenger(i);
                    passenger.setRequiredFloor(passengerService
                            .determineRequiredFloor(FIRST_FLOOR, floorsQuantity, i));
                    passenger.setDirection(passengerService
                            .determineDirection(i, passenger.getRequiredFloor()));
                    passengersOnTheFloor.add(passenger);
                }
            }
            passengersInBuilding.put(i, passengersOnTheFloor);
        }
        return passengersInBuilding;
    }
}
