package dbproject;

import dbproject.db.DBConnector;
import dbproject.usecases.AddExercise;
import dbproject.usecases.PrintExercise;
import dbproject.usecases.PrintHelp;
import dbproject.usecases.UseCaseInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    DBConnector dbConnector;
    UseCaseInterface addExerciseUsecase;
    UseCaseInterface printExercisesUsecase;
    UseCaseInterface printHelpUsecase;

    public Main() {
        dbConnector = new DBConnector();
        addExerciseUsecase = new AddExercise();
        printExercisesUsecase = new PrintExercise();
        printHelpUsecase = new PrintHelp();
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
                    case "printExercises":
                        printExercisesUsecase.runUseCase(scanner);
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
