package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogMeal;
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
            }
        } catch(Exception e){
            CommandUi.printErrorMessage("An error occurred while adding the meal: " + e.getMessage());
        }
    }

    public String getDescription() {
        return description;
    }

    public int getServings() {
        return servings;
    }

    public int getCaloriesPerSaving() {
        return caloriesPerSaving;
    }

    private int getCalorieGoal() {
        for (Log log : LogList.logList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Calorie")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0;
    }

    private int calculateTotalCaloriesConsumed() {
        int totalCaloriesConsumed = 0;
        for (Log log : LogList.logList) {
            if (log instanceof LogMeal) {
                totalCaloriesConsumed += ((LogMeal) log).getMealCalories();
            }
        }
        return totalCaloriesConsumed;
    }
}
