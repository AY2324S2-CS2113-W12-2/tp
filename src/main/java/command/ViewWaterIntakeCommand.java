package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogWater;
import activeedge.ui.CommandUi;

import java.util.ArrayList;

/**
 * Represents a command to view water intake statistics.
 */
public class ViewWaterIntakeCommand extends Command {

    /**
     * Executes the command to view water intake statistics.
     */
    public void execute() {
        int totalWaterIntake = getTotalWaterIntake(LogList.logList);
        int waterGoal = getWaterGoal(LogList.logList);
        CommandUi.printWaterIntakeMessage(totalWaterIntake, waterGoal);

    }

    /**
     * Calculates the total water intake from the log list.
     *
     * @param logList The list of logs to calculate total water intake from.
     * @return The total water intake.
     */
    int getTotalWaterIntake(ArrayList<Log> logList) {
        int totalWaterIntake = 0;
        for (Log log : logList) {
            if (log instanceof LogWater) {
                totalWaterIntake += ((LogWater) log).getQuantity();
            }
        }
        return totalWaterIntake;
    }

    public int getWaterGoal(ArrayList<Log> logList) {
        for (Log log : logList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Water")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0; // Default to 0 if no water goal is found
    }

}
