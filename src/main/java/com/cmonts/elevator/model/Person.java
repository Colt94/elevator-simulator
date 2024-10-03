package com.cmonts.elevator.model;

/**
 * Person class represents an individual using the elevator, with information about their name,
 * current floor, and destination floor.
 */
public class Person {

    private String name;
    private int currentFloor;
    private int destinationFloor;

    /**
     * Constructor for Person class.
     *
     * @param name The name of the person.
     * @param currentFloor The current floor where the person is located.
     * @param destinationFloor The floor the person wishes to travel to.
     */
    public Person(String name, int currentFloor, int destinationFloor) {
        this.name = name;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current floor where the person is located.
     *
     * @return The current floor.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Sets the current floor where the person is located.
     *
     * @param currentFloor The current floor to set.
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Returns the destination floor the person wishes to travel to.
     *
     * @return The destination floor.
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * Sets the destination floor the person wishes to travel to.
     *
     * @param destinationFloor The destination floor to set.
     */
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
