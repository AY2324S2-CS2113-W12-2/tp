package command;

import activeedge.task.GoalTask;
import activeedge.task.Task;
import activeedge.task.TaskList;
import activeedge.ui.CommandUi;
import activeedge.task.MealTask;
import static activeedge.task.TaskList.tasksList;

public class LogMealCommand extends Command{
    protected String description;
    protected int servings;
    protected int mealCalories;
    protected String date;
    protected String time;
    protected boolean isItemPresentInFoodData;

    public LogMealCommand(String description, int servings, int mealCalories, String date, String time,
                          boolean isItemPresentInFoodData) {
        this.description = description;
        this.servings = servings;
        this.mealCalories = mealCalories;
        this.date = date;
        this.time = time;
        this.isItemPresentInFoodData = isItemPresentInFoodData;
    }

    public void execute() {
        try {
            int calorieGoal = getCalorieGoal();
            if (isItemPresentInFoodData) {
                MealTask logMeal = new MealTask(description, servings, mealCalories, date, time);
                int totalCaloriesConsumed = calculateTotalCaloriesConsumed() + mealCalories;
                tasksList.add(logMeal);
                CommandUi.printMealLogMessage(logMeal);

                if (totalCaloriesConsumed > calorieGoal) {
                    CommandUi.printCalorieExceedingWarning();
                }
            } else {
                CommandUi.printFoodItemNotFoundMessage(description);
            }
        } catch(Exception e){
            CommandUi.printErrorMessage("An error occurred while logging the meal: " + e.getMessage());
        }
    }


    public int getMealCalories() {
        return mealCalories;
    }

    public String getDescription() {
        return description;
    }

    public int getServings() {
        return servings;
    }

    private int getCalorieGoal() {
        for (Task task : TaskList.tasksList) {
            if (task instanceof GoalTask && task.getDescription().startsWith("Calorie")) {
                return ((GoalTask) task).getGoalAmount();
            }
        }
        return 0;
    }

    private int calculateTotalCaloriesConsumed() {
        int totalCaloriesConsumed = 0;
        for (Task task : TaskList.tasksList) {
            if (task instanceof MealTask) {
                totalCaloriesConsumed += ((MealTask) task).getMealCalories();
            }
        }
        return totalCaloriesConsumed;
    }
}
