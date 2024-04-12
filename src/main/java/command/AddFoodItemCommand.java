package command;

import activeedge.task.GoalTask;
import activeedge.task.MealTask;
import activeedge.task.Task;
import activeedge.task.TaskList;
import activeedge.ui.CommandUi;

import static activeedge.FoodData.foodItems;
import static activeedge.FoodData.appendItem;
import activeedge.FoodData;

public class AddFoodItemCommand extends Command {
    protected String description;
    protected int servings;
    protected int caloriesPerSaving;
    protected String date;
    protected String time;

    public AddFoodItemCommand(String description, int servings, int caloriesPerSaving, String date, String time) {
        this.description = description;
        this.servings = servings;
        this.caloriesPerSaving = caloriesPerSaving;
        this.date = date;
        this.time = time;
    }

    public void execute() {
        try {
            int calorieGoal = getCalorieGoal();
            if (FoodData.foodItemExists(description)) {
                // Food item exists, prompt user to log it
                CommandUi.promptLogFoodMessage(description);
            } else {
                String[] newItem = {description, Integer.toString(caloriesPerSaving)};

                foodItems = appendItem(foodItems, newItem);
                CommandUi.printAddFoodItemMessage(description);
                LogMealCommand logMealCommand = new LogMealCommand(description, servings,
                        caloriesPerSaving * servings, date, time, true);
                int totalCaloriesConsumed = calculateTotalCaloriesConsumed() + caloriesPerSaving;
                logMealCommand.execute();
                if (totalCaloriesConsumed > calorieGoal) {
                    CommandUi.printCalorieExceedingWarning();
                }

            }
        } catch(Exception e){
            CommandUi.printErrorMessage("An error occurred while adding the meal: " + e.getMessage());
        }
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
