package ActiveEdge.Ui;

import static ActiveEdge.Task.TaskList.tasksList;

import ActiveEdge.Task.WaterTask;
import ActiveEdge.Task.LogMeals;

public class CommandUi {

    public static void printMealList() {
        System.out.println("Here are your logged meals for today");
        for (int i = 0; i < tasksList.size(); i++) {
            int index = 1 + i;
            System.out.println(index + ". " + tasksList.get(i));
        }
    }

    public static void printMealLogMessage(LogMeals logMeals) {
        System.out.println("You've logged " + Integer.toString(logMeals.getServings()) + " servings" + " of " + logMeals.getFoodName() + ".") ;
        System.out.println("Total calories: " + Integer.toString(logMeals.getMealCalories()));
    }

    public static void printShowCalMessage() {
    }

    public static void printWaterLogMessage(WaterTask newWaterTask) {
        System.out.println("Successfully logged " + newWaterTask.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake, int waterGoal) {
        double percentage = ((double) totalWaterIntake / waterGoal) * 100;
        System.out.println("Total water consumed today: " + totalWaterIntake + " ml (" + String.format("%.0f%%", percentage) + " of " + waterGoal + "ml goal).");
    }
}
