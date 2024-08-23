package com.example.elevatorlld;

public class Request {

    private final int sourceFloor;
    private final int destinationFloor;


    public Request(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getSourceFloor() {
        return this.sourceFloor;
    }

    public int getDestinationFloor() {
        return this.destinationFloor;
    }
}
