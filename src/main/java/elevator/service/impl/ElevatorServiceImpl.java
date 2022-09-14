package elevator.service.impl;

import elevator.model.Building;
import elevator.model.Direction;
import elevator.model.Elevator;
import elevator.model.Passenger;
import elevator.service.ElevatorService;
import elevator.service.PassengerService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ElevatorServiceImpl implements ElevatorService {
    private final PassengerService passengerService = new PassengerServiceImpl();
    private final Building building;
    private final Elevator elevator;

    public ElevatorServiceImpl(Building building) {
        this.building = building;
        elevator = new Elevator();
        takePassengers();
    }

    @Override
    public void moveOn() {
        checkFloorAndDirection();
        changeFloor();
        System.out.println("Elevator is moving " + elevator.getDirection() + "....."
                + System.lineSeparator());
        if (elevator.getCurrentPassengers().isEmpty()
                && building.getPassengersOnFloors().get(elevator
                        .getCurrentFloor()).isEmpty()) {
            nextFloorWhenElevatorAndFloorAreEmpty();
        }
        System.out.println("Elevator on the " + elevator.getCurrentFloor() + " floor.");
        dropPassengers();
        takePassengers();
        if (!elevator.getCurrentPassengers().isEmpty()) {
            countMaximalFloor();
        }
    }

    private void checkFloorAndDirection() {
        if (elevator.getCurrentFloor() == building.getFloorsQuantity()) {
            elevator.setDirection(Direction.DOWN);
            return;
        }
        if (elevator.getCurrentFloor() == 1) {
            elevator.setDirection(Direction.UP);
        }
    }

    private void changeFloor() {
        if (elevator.getDirection().equals(Direction.UP)) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        } else {
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
        }
    }

    private int countMaximalFloor() {

        int finalFloor = elevator.getCurrentPassengers().stream()
                .map(Passenger::getRequiredFloor)
                .mapToInt(f -> f)
                .max().orElseThrow(NoSuchElementException::new);
        System.out.println("Elevator will move "
                + elevator.getDirection().toString()
                + " to " + finalFloor);
        return finalFloor;
    }

    private int nextFloorWhenElevatorAndFloorAreEmpty() {
        Integer theNextFloor = Collections.max(building.getPassengersOnFloors().keySet());
        elevator.setCurrentFloor(theNextFloor);
        return theNextFloor;
    }

    private List<Passenger> takePassengers() {
        int newPassengersQuantity = 0;
        List<Passenger> passengersOnCurrentFloor = building
                .getPassengersOnFloors()
                .get(elevator.getCurrentFloor());
        if (passengersOnCurrentFloor != null) {
            for (Passenger passenger : passengersOnCurrentFloor) {
                if (elevator.getCurrentPassengers().size() < 5
                        && passenger.getDirection().equals(elevator.getDirection())) {
                    elevator.getCurrentPassengers().add(passenger);
                    passenger.setInElevator(true);
                    newPassengersQuantity++;
                }
            }
        }
        System.out.println(newPassengersQuantity + " passengers boarded the elevator. There are "
                + (5 - elevator.getCurrentPassengers().size()) + " vacancies");
        return passengersOnCurrentFloor;
    }

    private void dropPassengers() {
        if (elevator.getCurrentPassengers().isEmpty()) {
            System.out.println("The elevator has not passengers! Try to find new.");
            return;
        }
        int quitedPassengersQuantity = 0;
        List<Passenger> newList = new ArrayList<>(elevator.getCurrentPassengers());
        for (Passenger passenger : newList) {
            if (passenger.getRequiredFloor() == elevator.getCurrentFloor()) {
                passenger.setInElevator(false);
                passenger.setCurrentFloor(elevator.getCurrentFloor());
                passenger.setRequiredFloor(passengerService.determineRequiredFloor(
                        1,
                        building.getFloorsQuantity(),
                        elevator.getCurrentFloor()));
                elevator.getCurrentPassengers().remove(passenger);
                quitedPassengersQuantity++;
            }
        }
        System.out.println(quitedPassengersQuantity + " passengers quited the elevator");
    }
}
