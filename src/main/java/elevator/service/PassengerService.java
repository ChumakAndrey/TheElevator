package elevator.service;

import elevator.model.Direction;
import elevator.model.Passenger;

public interface PassengerService {
    Passenger createPassenger(int i);

    int determineRequiredFloor(int minFloor, int maxFloor, int currentFloor);

    Direction determineDirection(int currentFloor, int nextFloor);
}
