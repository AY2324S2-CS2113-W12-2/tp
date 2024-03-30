package command;

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

    public void execute() throws ActiveEdgeException {
        if (isItemPresentInFoodData){
            MealTask logMeal = new MealTask(description, servings, mealCalories, dateTime);
            tasksList.add(logMeal);
            CommandUi.printMealLogMessage(logMeal);
        } else {
            CommandUi.printFoodItemNotFoundMessage(description);
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
}
