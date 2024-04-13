package command;

import activeedge.log.LogGoals;

import static activeedge.log.LogList.logList;

public class AddCalorieGoalCommand {
    private int calorieGoal;
    private String date;
    private String time;

    public AddCalorieGoalCommand(int calorieGoal, String date, String time) {
        this.calorieGoal = calorieGoal;
        this.date = date;
        this.time = time;
    }

    public void execute() {
        LogGoals logCalorieGoal = new LogGoals("Calorie", calorieGoal, date, time);

        logList.add(logCalorieGoal);
        // Add logic to save calorie goal to log list
    }
}
