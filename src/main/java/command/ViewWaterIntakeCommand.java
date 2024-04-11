package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogWater;
import activeedge.ui.CommandUi;
<<<<<<< HEAD
import activeedge.task.Task;
import activeedge.task.LogWater;
=======
>>>>>>> cb76f05 (change all task to logs, fixed summary function)

import java.util.ArrayList;

public class ViewWaterIntakeCommand {
    public void execute() {
        int totalWaterIntake = getTotalWaterIntake(LogList.logList);
        int waterGoal = getWaterGoal(LogList.logList);
        CommandUi.printWaterIntakeMessage(totalWaterIntake, waterGoal);

    }

    int getTotalWaterIntake(ArrayList<Log> logList) {
        int totalWaterIntake = 0;
<<<<<<< HEAD
        for (Task task : tasksList) {
            if (task instanceof LogWater) {
                totalWaterIntake += ((LogWater) task).getQuantity();
=======
        for (Log log : logList) {
            if (log instanceof LogWater) {
                totalWaterIntake += ((LogWater) log).getQuantity();
>>>>>>> cb76f05 (change all task to logs, fixed summary function)
            }
        }
        return totalWaterIntake;
    }

    public int getWaterGoal(ArrayList<Log> tasksList) {
        for (Log log : tasksList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Water")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0; // Default to 0 if no water goal is found
    }

}
