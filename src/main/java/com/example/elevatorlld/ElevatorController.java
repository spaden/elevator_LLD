package com.example.elevatorlld;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private final List<Elevator> elevatorList;

    public ElevatorController(int numElevators, int capacity) {
        elevatorList = new ArrayList<>();
        for (int i=0; i< numElevators; i++) {
            Elevator elevator = new Elevator(i+1, capacity, (int)(Math.random() * 10) + 1 );
            elevatorList.add(elevator);
            new Thread(elevator::run).start();
        }
    }

    public void requestElevator(int sourceFloor, int destinationFloor) {
        Elevator optimalElevator = findOptimalElevator(sourceFloor, destinationFloor);
        optimalElevator.addRequest(new Request(sourceFloor, destinationFloor));
    }

    public Elevator findOptimalElevator(int sourceFloor, int destinationFloor) {
        Elevator optimalElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator: elevatorList) {
            int distance = Math.abs(sourceFloor - elevator.getCurrentFloor());
            if(distance < minDistance) {
                minDistance = distance;
                optimalElevator = elevator;
            }
        }
        return optimalElevator;
    }

}
