package command;

import activeedge.task.Task;
import activeedge.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static activeedge.Storage.saveLogsToFile;
import static activeedge.task.TaskList.tasksList;

/**
 * The ChangeWaterGoalCommand class represents a command to change the user's daily water goal.
 * It prompts the user to set a new water goal and updates it in the task list.
 */
public class ChangeWaterGoalCommand {

    /**
     * Executes the command to change the user's daily water goal.
     * Prompts the user to set a new water goal and updates it in the task list.
     *
     * @throws ActiveEdgeException if an error occurs during the execution of the command.
     */
    public static void execute() throws ActiveEdgeException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        // Remove existing water goal task
        for (int i = 0; i < tasksList.size(); i++) {
            Task tasksList = TaskList.tasksList.get(i);
            if (tasksList.toString().startsWith("Goal Water")) {
                TaskList.delete(i);
            }
        }

        Scanner scanner = new Scanner(System.in);

        int waterGoal = 0;
        int l = 0;
        while (l < 1) {
            System.out.println("Please set your new daily water goal (in ml): ");
            try {
                waterGoal = Integer.parseInt(scanner.nextLine());
                if (waterGoal >= 1 && waterGoal <= 6000) {
                    AddWaterGoalCommand addWaterGoalCommand = new AddWaterGoalCommand(waterGoal, date, time);
                    addWaterGoalCommand.execute();
                    System.out.println("You have successfully changed your water goal! You can continue logging data!");
                    saveLogsToFile("data/data.txt");
                    l++;
                } else {
                    System.out.println("Please input a positive integer between 1 and 6000!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a positive integer between 1 and 6000!");
            }
        }
    }
}
