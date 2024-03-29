package dbproject.usecases;

import java.util.List;
import java.util.Scanner;

import dbproject.db.Workout;

public class PrintWorkoutPeriod implements UseCaseInterface{

	@Override
	public void runUseCase(Scanner scanner) {
		System.out.println("Enter the date the period begins, master:");
		System.out.print(">> ");
		String weekStart = scanner.nextLine();
		System.out.println("Enter the date the period ends, master:");
		System.out.print(">> ");
		String weekEnd = scanner.nextLine();
		List<Workout> week = Workout.workoutWeek(weekStart, weekEnd);
		if(week.isEmpty()) {
			System.out.println("There are no workouts that period, master!");
			return;
		}
		
		System.out.println("Here is a list of all the workouts that period, master:");
		for(Workout workout : week) {
			System.out.println();
			System.out.println("Name: \t\t\t" + workout.getName());
			System.out.println("Time: \t\t\t" + workout.getTime());
			System.out.println("Duration: \t\t" + workout.getDuration());
			System.out.println("Condition: \t\t" + workout.getCondition());
			System.out.println("Notes: \t\t\t" + workout.getNotes());
		}
		System.out.println();
	}

}
