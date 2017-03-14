package dbproject.usecases;

import dbproject.db.Exercise;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ela49 on 14.03.2017.
 */
public class PrintExercise implements UseCaseInterface {

    @Override
    public void runUseCase(Scanner scanner) {
        List<Exercise> exercises = Exercise.getAll();
        if(exercises.isEmpty()) {
            System.out.println("There are no exercises, master!");
            return;
        }

        System.out.println("Here is a list of all the exercises, master:");
        for(Exercise exercise : exercises) {
            System.out.println();
            System.out.println("Name: \t\t\t" + exercise.getName());
            System.out.println("Description: \t" + exercise.getDescription());
        }
        System.out.println();
    }
}
