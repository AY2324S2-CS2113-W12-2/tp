package command;


import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.ui.GoalsUi;

/**
 * The ShowGoalsCommand class represents a command to display the current goals set by the user.
 * It retrieves the calorie and water goals from the TaskList and prints them using the GoalsUi.
 */
public class ShowGoalsCommand {

    /**
     * Executes the command by iterating through the list of tasks,
     * retrieving the calorie and water goals, and printing them using GoalsUi.
     */
    public void execute() {
        int calorieGoal = 0; // The calorie goal set by the user
        int waterGoal = 0; // The water goal set by the user

        // Iterate through the list of logs
        for (Log log : LogList.logList) {
            // Check if the log is an instance of LogGoals
            if (log instanceof LogGoals) {
                LogGoals logGoals = (LogGoals) log; // Cast Log to LogGoals
                // Check if the goal is related to calories
                if (logGoals.getDescription().startsWith("Calorie")) {
                    calorieGoal = logGoals.getGoalAmount(); // Retrieve calorie goal
                } else if (logGoals.getDescription().startsWith("Water")) {
                    waterGoal = logGoals.getGoalAmount(); // Retrieve water goal
                }
            }
        }

        // Print the goals using GoalsUi
        GoalsUi.printShowGoalsMessage(calorieGoal, waterGoal);
    }
}

