package elevator;

import elevator.model.Building;
import elevator.service.BuildingService;
import elevator.service.ElevatorService;
import elevator.service.impl.BuildingServiceImpl;
import elevator.service.impl.ElevatorServiceImpl;

public class Main {

    public static void main(String[] args) {
        BuildingService buildingService = new BuildingServiceImpl();
        Building building = buildingService.createBuilding();
        ElevatorService elevatorService = new ElevatorServiceImpl(building);

        for (int i = 0; i < 100; i++) {

            elevatorService.moveOn();
        }
    }
}
