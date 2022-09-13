package elevator.service.impl;

import elevator.model.Building;
import elevator.model.Direction;
import elevator.model.Passenger;
import elevator.service.PassengerService;
import elevator.util.Randomizer;


public class PassengerServiceImpl implements PassengerService {

    public PassengerServiceImpl() {
    }

    @Override
    public Passenger createPassenger(int currentFloor) {
        Passenger passenger = new Passenger();
        passenger.setCurrentFloor(currentFloor);
        return passenger;
    }

    @Override
    public int determineRequiredFloor(int minFloor, int maxFloor, int currentFloor) {
        int nextRequiredFloor = currentFloor;
        while (nextRequiredFloor == currentFloor) {
            nextRequiredFloor = Randomizer.getRandomQuantity(minFloor, maxFloor);
        }
        return nextRequiredFloor;
    }

    @Override
    public Direction determineDirection(int currentFloor, int nextFloor) {
        return currentFloor < nextFloor ? Direction.UP : Direction.DOWN;
    }
}
