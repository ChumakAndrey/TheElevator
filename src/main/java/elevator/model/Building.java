package elevator.model;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.Data;

@Data
public class Building {
    private final int floorsQuantity;
    private final Map<Integer, List<Passenger>> passengersOnFloors;

    public Building(int floorsQuantity, Map<Integer, List<Passenger>> passengersOnFloors) {
        this.floorsQuantity = floorsQuantity;
        this.passengersOnFloors = passengersOnFloors;
        System.out.println("Building was created. It has " + this.floorsQuantity + " floors");
    }


}
