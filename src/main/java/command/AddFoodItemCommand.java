package command;

import activeedge.ui.CommandUi;

import static activeedge.FoodData.foodItems;
import static activeedge.FoodData.appendItem;
import java.time.LocalDateTime;


public class AddFoodItemCommand {
    protected String description;
    protected int servings;
    protected int caloriesPerSaving;
    protected LocalDateTime dateTime;

    public AddFoodItemCommand(String description, int servings, int caloriesPerSaving, LocalDateTime dateTime) {
        this.description = description;
        this.servings = servings;
        this.caloriesPerSaving = caloriesPerSaving;
        this.dateTime = dateTime;
    }

    public void execute() throws ActiveEdgeException {
        String[] newItem = {description, Integer.toString(caloriesPerSaving)};

        foodItems = appendItem(foodItems, newItem);
        CommandUi.printAddFoodItemMessage(description);
        LogMealCommand logMealCommand = new LogMealCommand(description, servings,
                caloriesPerSaving*servings, dateTime, true);
        logMealCommand.execute();
    }
}