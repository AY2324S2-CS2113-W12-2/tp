package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogMeal;
import activeedge.ui.CommandUi;

import static activeedge.FoodData.foodItems;
import static activeedge.FoodData.appendItem;
import activeedge.FoodData;

/**
 * This class is responsible for adding a new food item to the food database if it doesn't already exist and
 * logging it. If the item exists, it prompts the user to log the food item.
 */
public class AddFoodItemCommand extends Command {
    protected String description;
    protected int servings;
    protected int caloriesPerSaving;
    protected String date;
    protected String time;


    /**
     * Constructs a new AddFoodItemCommand with specified details of the food item.
     *
     * @param description the description of the food item
     * @param servings the number of servings
     * @param caloriesPerSaving the number of calories per serving
     * @param date the date when the item is added
     * @param time the time when the item is added
     */
    public AddFoodItemCommand(String description, int servings, int caloriesPerSaving, String date, String time) {
        this.description = description;
        this.servings = servings;
        this.caloriesPerSaving = caloriesPerSaving;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to add a food item or prompt for logging based on its existence.
     * It first checks if the food item exists in the database, prompts for logging if it does,
     * otherwise adds it to the database and logs it.
     */
    public void execute() {
        try {
            int calorieGoal = getCalorieGoal();

            //Check if the food item already exist and show the necessary steps.
            if (FoodData.foodItemExists(description)) {
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

    /**
     * Retrieves the calorie goal of the user.
     *
     * @return the calorie goal or zero if not set
     */
    private int getCalorieGoal() {
        for (Log log : LogList.logList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Calorie")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0;
    }

    /**
     * Calculates the total calories consumed from all meal logs in the log list.
     *
     * @return the total calories consumed
     */
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
