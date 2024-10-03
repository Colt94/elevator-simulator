package com.cmonts.elevator.model;

import java.util.List;

/**
 * Floor class represents a floor in the building, containing people waiting for the elevator,
 * the floor number, and the direction they want to travel.
 */
public class Floor {
    private List<Person> peopleWaiting;
    private int floorNumber;
    private String direction;

    /**
     * Default constructor for the Floor class.
     */
    public Floor() {}

    /**
     * Constructor for Floor class.
     *
     * @param peopleWaiting The list of people waiting on this floor.
     * @param floorNumber The floor number.
     * @param direction The direction people on this floor want to travel ("up" or "down").
     */
    public Floor(List<Person> peopleWaiting, int floorNumber, String direction) {
        this.peopleWaiting = peopleWaiting;
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    /**
     * Returns the list of people waiting on this floor for the elevator.
     *
     * @return A list of people waiting for the elevator.
     */
    public List<Person> getPeopleWaiting() {
        return peopleWaiting;
    }

    /**
     * Sets the list of people waiting on this floor for the elevator.
     *
     * @param peopleWaiting The list of people waiting for the elevator.
     */
    public void setPeopleWaiting(List<Person> peopleWaiting) {
        this.peopleWaiting = peopleWaiting;
    }

    /**
     * Returns the floor number of this floor.
     *
     * @return The floor number.
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets the floor number for this floor.
     *
     * @param floorNumber The floor number.
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Returns the direction people on this floor want to travel.
     *
     * @return The direction ("up" or "down").
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the direction people on this floor want to travel.
     *
     * @param direction The direction ("up" or "down").
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Returns a string representation of the people waiting on this floor.
     *
     * @return A comma-separated list of names of the people waiting.
     */
    public String printPeopleWaiting() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Person person : peopleWaiting) {
            stringBuilder.append(person.getName()).append(",");
        }
        return stringBuilder.toString();
    }
}
