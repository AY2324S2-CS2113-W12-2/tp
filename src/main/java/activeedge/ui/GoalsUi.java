package activeedge.ui;


/**
 * The GoalsUi class provides user interface functionalities for managing health goals in the ActiveEdge application.
 */
public class GoalsUi {

    /**
     * Prints the current health goals set by the user.
     * @param calorieGoal The daily calorie goal.
     * @param waterGoal The daily water intake goal.
     */
    public static void printShowGoalsMessage(int calorieGoal, int waterGoal) {
        System.out.println(" Current goals: \n Daily calories: " + calorieGoal + "kcal" +
                ", \n Daily water: " + waterGoal + "ml.");
    }
}

