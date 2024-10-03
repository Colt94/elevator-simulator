package com.cmonts.elevator.service;

import com.cmonts.elevator.Elevator;
import com.cmonts.elevator.model.Floor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * BuildingService class manages the elevator system within the building, including starting the elevator
 * and handling requests to call the elevator to different floors.
 */
@Service
public class BuildingService {

    private static final Logger logger = LoggerFactory.getLogger(BuildingService.class);
    private int maxFloors = 10;
    private Elevator elevator;

    /**
     * Constructor for BuildingService class.
     * Initializes an elevator with a starting floor of 3, a maximum of 10 floors, and a maximum capacity of 5 people.
     */
    public BuildingService() {
        elevator = new Elevator(3, maxFloors, 5);
    }

    /**
     * Starts the elevator in a new thread, allowing it to begin processing floor requests.
     */
    public void startElevator() {
        Thread elevatorThread = new Thread(this.elevator);
        elevatorThread.start();
    }

    /**
     * Calls the elevator to a specified floor by enqueuing the floor request.
     *
     * @param floor The floor requesting the elevator.
     * @throws InterruptedException If the elevator thread is interrupted.
     */
    public void callElevator(Floor floor) throws InterruptedException {
        logger.info("Elevator called to floor " + floor.getFloorNumber());
        this.elevator.enqueue(floor);
    }
}
