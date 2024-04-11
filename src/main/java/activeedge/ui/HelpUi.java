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
                "1. Log meal: `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`\n" +
                "2. Log water intake : `log w/<AMOUNT_OF_WATER>`\n" +
                "3. Log exercises: `log e/<EXERCISE_NAME> d/<DURATION>`\n" +
                "4. View daily goals: `show g`\n" +
                "5. View daily calorie intake: `show c`\n" +
                "6. View daily water intake: `show w`\n" +
                "7. View all logged entries: `list`\n" +
                "8. View all food items in the database: `foodData`\n" +
                "9. View all exercises in the database: `exerciseData`\n" +
                "10. Delete meal logs: `delete <MEAL_NAME>`.\n" +
                "11. Delete water logs: `delete <QUANTITY_OF_WATER> ml` \n" +
                "12. Delete exercise logs: `delete <EXERCISE_NAME>` \n" +
                "13. Log a meal not in the database: `add m/<MEAL_NAME> " +
                "c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>`\n" +
                "14. Log an exercise not in the database: `add e/<EXERCISE> " +
                "c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>`\n" +
                "15. Change user height: `change h`\n" +
                "16. Change user weight: `change w`\n" +
                "17. Change daily calorie intake goal: `change cg`\n" +
                "18. Change daily water intake goal: `change wg`\n" +
                "19. Show daily summary: `summary`\n" +
                "20. Search for entries: `find`\n" +
                "21. Get help: `help`\n" +
                "22. Clear all logged entries: `clear`\n" +
                "23. Exit the program: `bye`\n" +
                "Start tracking your health goals now!" );
    }
}
