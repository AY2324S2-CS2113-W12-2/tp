package activeedge.ui;

import command.ActiveEdgeException;

/**
 * The GoalsUi class provides user interface functionalities for managing health goals in the ActiveEdge application.
 */
public class GoalsUi {

    /**
     * Prints a message confirming the addition of a new health goal.
     * @param description The description of the goal ('c' for calories, 'w' for water).
     * @param goalAmount The amount of the goal to be achieved.
     */
    public static void printAddGoalMessage(String description, int goalAmount) {
        try {
            String goalType;
            String unit;
            if (description.equals("c")) {
                goalType = "calories";
                unit = "kcal";
            } else if (description.equals("w")) {
                goalType = "water";
                unit = "ml";
            } else {
                throw new ActiveEdgeException("Invalid goal description. " +
                        "Must be 'c' for calories or 'w' for water.");
            }
            System.out.println("Goal added: " + goalType + " with goal amount "
                    + goalAmount + " " + unit);
        } catch (ActiveEdgeException e) {
            System.out.println("Error: " + e.warning);
        }
    }

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

