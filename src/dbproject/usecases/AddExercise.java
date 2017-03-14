package dbproject.usecases;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ela49 on 14.03.2017.
 */
public class AddExercise implements UseCaseInterface {
    @Override
    public void runUseCase() {
        System.out.println("\r\nWelcome to the Exercise Addition Module (EAM)");
        System.out.println("Please enter your data below");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter exercise name: ");
            String name = scanner.nextLine();
            System.out.print("Enter exercise description: ");
            String description = scanner.nextLine();
        }
    }
}
