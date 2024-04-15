package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogMeal;
import activeedge.ui.CommandUi;

import static activeedge.log.LogList.logList;

/**
 * This class represents a command to log a meal entry into the user's health record.
 * It includes functionality to check if the meal item is in the food database, log the meal,
 * and check against the user's calorie goals.
 */
public class LogMealCommand extends Command{
    protected String description;
    protected int servings;
    protected int mealCalories;
    protected String date;
    protected String time;
    protected boolean isItemPresentInFoodData;

    /**
     * Constructs a LogMealCommand with specified details about the meal and its timing.
     *
     * @param description Description of the meal.
     * @param servings Number of servings.
     * @param mealCalories Calories per meal.
     * @param date Date of meal consumption.
     * @param time Time of meal consumption.
     * @param isItemPresentInFoodData Flag to indicate if the meal is in the food database.
     */
    public LogMealCommand(String description, int servings, int mealCalories, String date, String time,
                          boolean isItemPresentInFoodData) {
        this.description = description;
        this.servings = servings;
        this.mealCalories = mealCalories;
        this.date = date;
        this.time = time;
        this.isItemPresentInFoodData = isItemPresentInFoodData;
    }

    /**
     * Executes the meal logging command. It logs the meal if the item is found in the food database,
     * calculates total calories consumed, and checks if it exceeds the calorie goal.
     */
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
                CommandUi.printFoodItemNotFoundMessage(description, servings);
            }
        } catch(Exception e){
            CommandUi.printErrorMessage("An error occurred while logging the meal: " + e.getMessage());
        }
    }

    /**
     * Retrieves the meal's calorie information.
     *
     * @return the calories of the meal.
     */
    public int getMealCalories() {
        return mealCalories;
    }

    /**
     * Retrieves the meal description.
     *
     * @return the description of the meal.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the number of servings consumed.
     *
     * @return the number of servings.
     */
    public int getServings() {
        return servings;
    }

    // Retrieves the calorie goal from the log list, specifically from entries marked as "Calorie" goals.
    private int getCalorieGoal() {
        for (Log log : LogList.logList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Calorie")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0;
    }

    // Calculates the total calories consumed by summing up the calories from all meal logs.
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
