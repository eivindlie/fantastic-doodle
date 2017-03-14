package dbproject.usecases;
import dbproject.db.Workout;

import java.util.Scanner;

public class AddWorkout implements UseCaseInterface {

	@Override
	public void runUseCase(Scanner scanner) {
		System.out.println("\r\nWelcome to the Workout Addition Module (WAM)");
		System.out.println("Please enter your data below");
        System.out.print("Enter workout name: ");
        String name = scanner.nextLine();
        System.out.print("Enter workout Time: ");
        String time = scanner.nextLine();
        System.out.print("Enter exercise duration: ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter exercise condition: ");
        int condition = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter exercise performance: ");
        int performance = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter exercise notes: ");
        String notes = scanner.nextLine();
        System.out.println();

        Workout workout = new Workout(name, time, duration, condition, performance, notes);
        workout.save();
        
        while(true){
        	String x = scanner.nextLine();
        	if (x.equals("exit")){
        		break;
        	}
        	int id = Integer.parseInt(x);
        	workout.addExercise(id);
        }
	}
}

