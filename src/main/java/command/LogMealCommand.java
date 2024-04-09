package command;

import activeedge.task.GoalTask;
import activeedge.task.Task;
import activeedge.ui.CommandUi;
import activeedge.task.MealTask;

import static activeedge.task.TaskList.tasksList;
import java.time.LocalDateTime;


public class LogMealCommand {
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
            if (isItemPresentInFoodData) {
                MealTask logMeal = new MealTask(description, servings, mealCalories, date, time);
                int totalCaloriesConsumed = calculateTotalCaloriesConsumed() + mealCalories;
                tasksList.add(logMeal);
                CommandUi.printMealLogMessage(logMeal);
                if (exceedsCalorieGoal(totalCaloriesConsumed)) {
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

    // Helper method to calculate the total calories consumed including the logged meal
    private int calculateTotalCaloriesConsumed() {
        int totalCaloriesConsumed = 0;
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i) instanceof MealTask) {
                totalCaloriesConsumed += ((MealTask) tasksList.get(i)).getMealCalories();
            }
        }
        return totalCaloriesConsumed;
    }

    // Helper method to check if the total calories consumed exceed the calorie goal
    private boolean exceedsCalorieGoal(int totalCaloriesConsumed) {
        for (Task task : tasksList) {
            if (task instanceof GoalTask && task.getDescription().equals("calories")) {
                int calorieGoal = ((GoalTask) task).getGoalAmount();
                return totalCaloriesConsumed > calorieGoal;
            }
        }
        return false;
    }

}
