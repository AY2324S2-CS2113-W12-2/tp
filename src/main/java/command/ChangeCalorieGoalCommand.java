

package command;

import activeedge.log.Log;
import activeedge.log.LogList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static activeedge.log.LogList.logList;

/**
 * This class represents a command to change the user's calorie goal.
 * It allows the user to input a new calorie goal within a specified range
 * and updates the user details accordingly.
 */
public class ChangeCalorieGoalCommand {

    /**
     * Executes the command to change the user's calorie goal.
     * Prompts the user to input a new calorie goal, validates the input,
     * and updates the user details accordingly. Logs the change and saves
     * it to a file.
     *
     * @throws ActiveEdgeException if there is an error executing the command.
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
                    AddCalorieGoalCommand addCalorieGoalCommand = new AddCalorieGoalCommand(newCalorieGoal, date, time);
                    addCalorieGoalCommand.execute();
                    System.out.println("You have successfully changed your calorie goal! " +
                            "You can continue logging data!");
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



