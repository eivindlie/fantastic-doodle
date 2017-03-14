package dbproject.usecases;

import java.util.Scanner;

import dbproject.db.Workout;

public class AddWorkout implements UseCaseInterface {

	
	public void runUseCase(){
		System.out.println("\r\nWelcome to the Workout Addition Module (WAM)");
		System.out.println("Please enter your data below");
        try (Scanner scanner = new Scanner(System.in)) {
        	System.out.println("Enter workout name: ");
        	String name = scanner.nextLine();
            System.out.print("Enter workout Time: ");
            String time = scanner.nextLine();
            System.out.print("Enter exercise duration: ");
            int duration = scanner.nextInt();
            System.out.println("Enter exercise condition: ");
            int condition = scanner.nextInt();
            System.out.println("Enter exercise performance: ");
            int performance = scanner.nextInt();
            System.out.println("Enter exercise notes: ");
            String notes = scanner.nextLine();
            Workout workout = new Workout(name, time, duration, condition, performance, notes);
            workout.save();
        }
	}
}
