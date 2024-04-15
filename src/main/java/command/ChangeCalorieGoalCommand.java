package command;

import activeedge.log.Log;
import activeedge.log.LogList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static activeedge.log.LogList.logList;

/**
 * Represents a command to change the user's daily calorie goal.
 * This command prompts the user to input a new calorie goal and validates
 * the input before updating the log list and executing the necessary actions.
 */

public class ChangeCalorieGoalCommand extends Command {

    /**
     * Executes the command to change the user's daily calorie goal.
     * Prompts the user to input a new calorie goal, validates the input,
     * and updates the log list accordingly. It then executes the
     * AddCalorieGoalCommand to save the new goal and logs the change.
     */

    public void execute() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        for (int i = 0; i < logList.size(); i++) {
            Log logList = LogList.logList.get(i);
            if (logList.toString().startsWith("Goal Calorie")) {
                LogList.delete(i);
            }
        }

        Scanner scanner = new Scanner(System.in);

        int newCalorieGoal = 0;
        int i = 0;
        while (i < 1) {
            System.out.println("Please input your new daily calorie goal: ");
            try {
                newCalorieGoal = Integer.parseInt(scanner.nextLine());
                if (newCalorieGoal >= 1 && newCalorieGoal <= 10000) {
                    AddCalorieGoalCommand addCalorieGoalCommand = new
                            AddCalorieGoalCommand(newCalorieGoal, date, time);
                    addCalorieGoalCommand.execute();
                    System.out.println("You have successfully changed your calorie goal! " +
                            "You can continue to log your data!");
                    i++;
                } else {
                    System.out.println("Please input a positive integer between 1 and 10000!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a positive integer between 1 and 10000!");
            }
        }
    }
}


