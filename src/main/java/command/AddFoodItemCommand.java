package command;

import activeedge.ui.CommandUi;

import static activeedge.FoodData.foodItems;
import static activeedge.FoodData.appendItem;
import activeedge.FoodData;

public class AddFoodItemCommand {
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

    public void execute() throws ActiveEdgeException {
        if (FoodData.foodItemExists(description)) {
            // Food item exists, prompt user to log it
            CommandUi.promptLogFoodMessage(description);
        } else {
            String[] newItem = {description, Integer.toString(caloriesPerSaving)};

            foodItems = appendItem(foodItems, newItem);
            CommandUi.printAddFoodItemMessage(description);
            LogMealCommand logMealCommand = new LogMealCommand(description, servings,
                    caloriesPerSaving * servings, date, time, true);
            logMealCommand.execute();
        }
    }
}
