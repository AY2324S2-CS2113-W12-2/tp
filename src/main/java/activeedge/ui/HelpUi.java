package activeedge.ui;

/**
 * The HelpUi class provides a user interface for displaying help messages related to
 * the Health Tracker Bot. It outlines how to use various commands within the bot for
 * tracking calorie intake, water intake, and setting personal health goals.
 */
public class HelpUi {

    /**
     * Prints a detailed help message to the standard output.
     */
    public static void printHelpMessage() {
        System.out.println("Welcome to Active Edge! Here are the available commands:\n" +
                "Log meals: log m/FOOD s/NUMBER_OF_SERVINGS\n" +
                "Add meals to database: add m/[FOOD] c/[CALORIES_PER_SERVING(kCal)] s/[NUMBER_OF_SERVINGS]\n" +
                "View daily calories: show c\n" +
                "Log water: log w/VOLUME_OF_WATER\n" +
                "View water intake: show w\n" +
                "Set daily calorie goal: set goal c/CALORIE_GOAL\n" +
                "Set daily water goal: set goal w/WATER_GOAL\n" +
                "View goals: show g\n" +
                "Log exercise: log e/EXERCISE_NAME d/DURATION\n" +
                "Add exercises to database: add e/[EXERCISE_NAME] c/[CALORIES_BURNT_PER_MINUTE] " +
                "d/[DURATION_IN_MINUTES]\n" +
                "View exercises: list exercises\n" +
                "Help: help\n" +
                "Find: find <KEYWORD>\n" +
                "Delete items from list: delete 'Quantity of water/Food name'\n" +
                "View all logged entries.: list\n" +
                "Clear: clear\n" +
                "Start tracking your health goals now!" );
    }
}
