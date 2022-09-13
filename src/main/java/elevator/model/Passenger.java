package elevator.model;

import lombok.Data;

@Data
public class Passenger {
    private boolean isInElevator = false;
    private int currentFloor;
    private int requiredFloor;
    private Direction direction;
}
