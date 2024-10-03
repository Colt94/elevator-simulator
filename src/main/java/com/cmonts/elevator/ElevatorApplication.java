package com.cmonts.elevator;

import com.cmonts.elevator.model.Floor;
import com.cmonts.elevator.model.Person;
import com.cmonts.elevator.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ElevatorApplication {
	private static final Logger logger = LoggerFactory.getLogger(ElevatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ElevatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner runCustomCode(BuildingService buildingService) {
		return args -> {

			// Start the elevator
			buildingService.startElevator();

			// Create some floors that were called
			Person person1 = new Person("Jim", 1, 7);
			Person person2 = new Person("Mark", 1, 5);
			ArrayList<Person> peopleWaiting1 = new ArrayList<>();
			peopleWaiting1.add(person1);
			peopleWaiting1.add(person2);

			// Simulate a floor calling the elevator with a direction
			Floor floor1 = new Floor(peopleWaiting1, person2.getCurrentFloor(), "up");

			buildingService.callElevator(floor1);

			// Create some floors that were called
			Person person3 = new Person("Sally", 6, 2);
			Person person4 = new Person("Jane", 6, 1);
			ArrayList<Person> peopleWaiting2 = new ArrayList<>();
			peopleWaiting2.add(person3);
			peopleWaiting2.add(person4);

			// Simulate a floor calling the elevator with a direction
			Floor floor2= new Floor(peopleWaiting2, person3.getCurrentFloor(), "down");

			buildingService.callElevator(floor2);

			// Create some floors that were called
			Person person5 = new Person("Cindy", 10, 1);
			Person person6 = new Person("Polly", 10, 1);
			ArrayList<Person> peopleWaiting3 = new ArrayList<>();
			peopleWaiting3.add(person5);
			peopleWaiting3.add(person6);

			// Simulate a floor calling the elevator with a direction
			Floor floor3= new Floor(peopleWaiting3, person5.getCurrentFloor(), "down");

			buildingService.callElevator(floor3);
		};
	}

}
