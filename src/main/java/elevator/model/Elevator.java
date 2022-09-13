package elevator.model;

import static elevator.util.Constants.ELEVATOR_CAPACITY;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.Data;

@Data
public class Elevator {
    private int currentFloor = 1;
    private int freeSpace = ELEVATOR_CAPACITY;
    private Direction direction = Direction.UP;
    private List<Passenger> currentPassengers = new LinkedList<>();

    public Elevator() {
        System.out.println("Elevator was created. Current floor: " +  currentFloor);

    }
}
