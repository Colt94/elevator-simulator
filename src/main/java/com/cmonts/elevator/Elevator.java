package com.cmonts.elevator;

import com.cmonts.elevator.model.Floor;
import com.cmonts.elevator.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Elevator class simulates an elevator system that can transport people between floors.
 * The elevator has a capacity limit, a set of floors it can travel between, and processes requests to move between floors.
 */
public class Elevator implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Elevator.class);
    List<Person> peopleInside = new LinkedList<>();
    ConcurrentLinkedDeque<Floor> calledQueue = new ConcurrentLinkedDeque<>();
    private int currentFloor;
    private int maxFloors;
    private final int maxCapacity;

    /**
     * Constructor for Elevator class.
     *
     * @param currentFloor The starting floor of the elevator.
     * @param maxFloors The maximum number of floors the elevator can travel to.
     * @param maxCapacity The maximum number of people the elevator can carry at one time.
     */
    public Elevator(int currentFloor, int maxFloors, int maxCapacity) {
        this.currentFloor = currentFloor;
        this.maxFloors = maxFloors;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Moves the elevator to the specified floor.
     *
     * @param floor The floor number to move to.
     */
    public void goToFloor(int floor) {
        if (floor != currentFloor) {
            logger.info("Elevator going to floor " + floor);
            try {
                // Simulate elevator travel time based on the distance between current and target floor.
                Thread.sleep(Math.abs((floor - currentFloor) * 1000L));
            } catch (InterruptedException e) {
                logger.info("Error simulating elevator speed");
            }
            currentFloor = floor;
        } else {
            logger.info("Already on floor " + floor);
        }
    }

    /**
     * Main logic for running the elevator. The elevator continuously services requested floors in the calledQueue.
     */
    @Override
    public void run() {
        while(true) {
            // Service floors as they are requested
            while (!calledQueue.isEmpty()) {
                Floor floor = calledQueue.remove();
                goToFloor(floor.getFloorNumber());
                // Pick up as many people as possible within the capacity
                while (peopleInside.size() < maxCapacity && floor.getPeopleWaiting().size() > 0) {
                    peopleInside.add(floor.getPeopleWaiting().remove(0));
                }
                logger.info("Picked up " + printPeopleInside());

                // Determine direction and service people accordingly
                String direction = floor.getDirection();
                if (direction.equals("up")) {
                    int i = currentFloor + 1;
                    while (i <= maxFloors && !peopleInside.isEmpty()) {
                        checkAndDropOff(i);
                        i++;
                    }
                } else {
                    int i = currentFloor - 1;
                    while (i >= 0 && !peopleInside.isEmpty()) {
                        checkAndDropOff(i);
                        i--;
                    }
                }
            }
            logger.info("Elevator idling");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checks if any person inside the elevator needs to get off at the current floor and drops them off.
     *
     * @param floorNum The floor number to check for drop-offs.
     */
    private void checkAndDropOff(int floorNum) {
        goToFloor(floorNum);
        Iterator<Person> it = peopleInside.iterator();
        while (it.hasNext()) {
            Person person = it.next();
            if (person.getDestinationFloor() == floorNum) {
                logger.info("Dropping off " + person.getName());
                it.remove();
            }
        }
    }

    /**
     * Enqueues a floor request to the elevator.
     *
     * @param floor The floor that the elevator has been called to.
     */
    public void enqueue(Floor floor) {
        this.calledQueue.add(floor);
    }

    /**
     * Returns the list of people currently inside the elevator.
     *
     * @return A list of people currently inside the elevator.
     */
    public List<Person> getPeopleInside() {
        return peopleInside;
    }

    /**
     * Sets the list of people inside the elevator.
     *
     * @param peopleInside A list of people to be inside the elevator.
     */
    public void setPeopleInside(List<Person> peopleInside) {
        this.peopleInside = peopleInside;
    }

    /**
     * Returns the queue of floor requests the elevator needs to service.
     *
     * @return The queue of floor requests.
     */
    public Queue<Floor> getCalledQueue() {
        return calledQueue;
    }

    /**
     * Sets the queue of waiting people to be serviced by the elevator.
     *
     * @param calledQueue The queue of people waiting for the elevator.
     */
    public void setPeopleWaiting(Queue<Person> calledQueue) {
        calledQueue = calledQueue;
    }

    /**
     * Gets the maximum number of floors the elevator can travel between.
     *
     * @return The maximum number of floors.
     */
    public int getMaxFloors() {
        return maxFloors;
    }

    /**
     * Sets the maximum number of floors the elevator can travel between.
     *
     * @param maxFloors The maximum number of floors.
     */
    public void setMaxFloors(int maxFloors) {
        this.maxFloors = maxFloors;
    }

    /**
     * Returns the current floor the elevator is on.
     *
     * @return The current floor.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Sets the current floor the elevator is on.
     *
     * @param currentFloor The current floor to set.
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Returns a string representation of people currently inside the elevator.
     *
     * @return A comma-separated list of people inside the elevator.
     */
    public String printPeopleInside() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Person person : peopleInside) {
            stringBuilder.append(person.getName()).append(",");
        }
        return stringBuilder.toString();
    }
}
