package dbproject.usecases;

import dbproject.db.Exercise;
import dbproject.db.Workout;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ela49 on 14.03.2017.
 */
public class PrintWorkouts implements UseCaseInterface {

    @Override
    public void runUseCase(Scanner scanner) {
        List<Workout> workouts = Workout.getAll();
        if(workouts.isEmpty()) {
            System.out.println("There are no workouts, master!");
            return;
        }

        System.out.println("Here is a list of all the workouts, master:");
        for(Workout workout : workouts) {
            String[][] cmds = {
                    {"Name:", workout.getName()},
                    {"Time:", workout.getTime()},
                    {"Duration:", Integer.toString(workout.getDuration())},
                    {"Condition:", Integer.toString(workout.getCondition())},
                    {"Performance:", Integer.toString(workout.getPerformance())},
                    {"Notes:", workout.getNotes()},
            };

            System.out.println();

            for(String[] row : cmds) {
                System.out.format("%-20s%s\n", row);
            }
            System.out.println();
        }
        System.out.println();
    }
}
