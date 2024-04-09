package command;

import activeedge.task.GoalTask;
import static activeedge.task.TaskList.tasksList;

public class AddWaterGoalCommand {
    private int waterGoal;
    private String date;
    private String time;

    public AddWaterGoalCommand(int waterGoal, String date, String time) {
        this.waterGoal = waterGoal;
        this.date = date;
        this.time = time;
    }

    public void execute() {
        GoalTask waterGoalTask = new GoalTask("Water", waterGoal, date, time);
        tasksList.add(waterGoalTask);
        // Add logic to save water goal to task list
    }
}
