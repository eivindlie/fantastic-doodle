package dbproject;

import dbproject.db.DBConnector;
import dbproject.usecases.*;

import java.util.Scanner;

public class Main {

    DBConnector dbConnector;
    UseCaseInterface addExerciseUsecase;
    UseCaseInterface addWorkoutUsecase;
    UseCaseInterface printExercisesUsecase;
    UseCaseInterface printHelpUsecase;
    UseCaseInterface printWorkoutsUsecase;
    UseCaseInterface printWorkoutWeekUsecase;

    public Main() {
        dbConnector = new DBConnector();
        addExerciseUsecase = new AddExercise();
        addWorkoutUsecase = new AddWorkout();
        printExercisesUsecase = new PrintExercise();
        printHelpUsecase = new PrintHelp();
        printWorkoutsUsecase = new PrintWorkouts();
        printWorkoutWeekUsecase = new PrintWorkoutPeriod();
    }

    public void run() {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Fantastic Doodle eXercise Manager!");
            System.out.println("Enter your command, master:");
            System.out.print(">> ");
            String input = scanner.nextLine();
            while(!input.equals("exit")) {
                switch(input) {
                    case "help":
                        printHelpUsecase.runUseCase(scanner);
                        break;
                    case "addExercise":
                        addExerciseUsecase.runUseCase(scanner);
                        break;
                    case "addWorkout":
                        addWorkoutUsecase.runUseCase(scanner);
                        break;
                    case "printExercises":
                        printExercisesUsecase.runUseCase(scanner);
                        break;
                    case "printWorkouts":
                        printWorkoutsUsecase.runUseCase(scanner);
                        break;
                    case "printWorkoutPeriod":
                        printWorkoutWeekUsecase.runUseCase(scanner);
                        break;
                }

                System.out.println("Enter your command, master:");
                System.out.print(">> ");
                input = scanner.nextLine();
            }
        }
    }

    public static void main(String... args) {
        Main main = new Main();
        main.run();
    }
}
