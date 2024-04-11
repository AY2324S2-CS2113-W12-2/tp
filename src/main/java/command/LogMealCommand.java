package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogMeal;
import activeedge.ui.CommandUi;

import static activeedge.log.LogList.logList;

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
            int calorieGoal = getCalorieGoal();
            if (isItemPresentInFoodData) {
                LogMeal logMeal = new LogMeal(description, servings, mealCalories, date, time);
                int totalCaloriesConsumed = calculateTotalCaloriesConsumed() + mealCalories;
                logList.add(logMeal);
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
        for (Log log : LogList.logList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Calorie")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0; // Return 0 if the calorie goal is not found
    }

    private int calculateTotalCaloriesConsumed() {
        int totalCaloriesConsumed = 0;
        for (Log log : logList) {
            if (log instanceof LogMeal) {
                totalCaloriesConsumed += ((LogMeal) log).getMealCalories();
            }
        }
        return totalCaloriesConsumed;
    }
}
