package command;

import activeedge.task.GoalTask;
import java.time.LocalDateTime;
import static activeedge.task.TaskList.tasksList;

public class AddWaterGoalCommand {
    private int waterGoal;
    private LocalDateTime dateTime;

    public AddWaterGoalCommand(int waterGoal, LocalDateTime dateTime) {
        this.waterGoal = waterGoal;
        this.dateTime = dateTime;
    }

    public void execute() {
        GoalTask waterGoalTask = new GoalTask("Water", waterGoal, dateTime);
        tasksList.add(waterGoalTask);
        // Add logic to save water goal to task list
    }
}