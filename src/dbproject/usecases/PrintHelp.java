package dbproject.usecases;

import java.util.Scanner;

/**
 * Created by ela49 on 14.03.2017.
 */
public class PrintHelp implements UseCaseInterface {
    @Override
    public void runUseCase(Scanner scanner) {
        String[][] cmds = {
                {"addExercise", "Adds a new exercise"},
                {"addWorkout", "Adds a new workout"},
                {"help", "Prints this help menu"},
                {"printExercises", "Prints all exercises currently in the database"},
                {"printWorkouts", "Prints all workouts currently in the database"},
            };

        System.out.println();
        System.out.println("Here are all the commands available for you, master:");

        for(String[] row : cmds) {
            System.out.format("%-20s%s\n", row);
        }
        System.out.println();
    }
}
