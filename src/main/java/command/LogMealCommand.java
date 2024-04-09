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
    protected LocalDateTime dateTime;
    protected boolean isItemPresentInFoodData;

    public LogMealCommand(String description, int servings, int mealCalories, LocalDateTime dateTime,
                          boolean isItemPresentInFoodData) {
        this.description = description;
        this.servings = servings;
        this.mealCalories = mealCalories;
        this.dateTime = dateTime;
        this.isItemPresentInFoodData = isItemPresentInFoodData;
    }

    public void execute() {
        try {
            int calorieGoal = getCalorieGoal();
            if (isItemPresentInFoodData) {
                MealTask logMeal = new MealTask(description, servings, mealCalories, dateTime);
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

    // Helper method to calculate the total calories consumed including the logged meal
    private int calculateTotalCaloriesConsumed() {
        int totalCaloriesConsumed = 0;
        for (Task task : tasksList) {
            if (task instanceof MealTask) {
                totalCaloriesConsumed += ((MealTask) task).getMealCalories();
            }
        }
        return totalCaloriesConsumed;
    }
    private int getCalorieGoal() {
        GoalTask calorieGoalTask = findCalorieGoalTask();
        if (calorieGoalTask != null) {
            int calorieGoal = calorieGoalTask.getGoalAmount();
            System.out.println("Retrieved calorie goal: " + calorieGoal); // Debug statement
            return calorieGoal;
        } else {
            System.out.println("Calorie goal task not found!"); // Debug statement
            // Handle the case where the calorie goal task is not found
            // For example, you might return a default value or throw an exception
            return 0; // Default value, replace it with your actual handling logic
        }
    }

    private GoalTask findCalorieGoalTask() {
        for (Task task : tasksList) {
            if (task instanceof GoalTask && task.getDescription().equals("calories")) {
                return (GoalTask) task;
            }
        }
        return null;
    }
}
