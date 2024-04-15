package activeedge.ui;

import static activeedge.log.LogList.logList;

import activeedge.log.Log;
import activeedge.log.LogExercise;
import activeedge.log.LogMeal;
import activeedge.log.LogWater;

import java.time.format.DateTimeFormatter;

/**
 * The CommandUi class provides static methods to print various types of messages to the console.
 * These messages include user prompts, error messages, warnings, and notifications
 * related to food and exercise logs, as well as daily summaries and status updates.
 * The class acts as a user interface helper, centralizing the message output functionality for
 * ease of modification and consistency across the application.
 */
public class CommandUi {

    static final String LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    /**
     * Prints a welcome message to the console with the application logo and a motivational tagline.
     */
    public static void printWelcomeMessage() {
        String logo = "ACTIVE EDGE!";
        System.out.println("Welcome to " + logo);
        System.out.println("Take the next step in your Healthy Lifestyle!");
    }

    /**
     * Prints a detailed list of all logged items for the day, categorized by food, water intake,
     * and exercises.
     */
    public static void printFullList() {
        System.out.println("Logged data for today:");

        System.out.println("Food: ");
        System.out.print(LINE);
        int j = 1;
        for (int i = 0; i < logList.size(); i++) {
            if (logList.get(i).toString().startsWith("Meal")) {
                System.out.println(j + ". " + logList.get(i).toString().substring(7));
                j++;
            }
        }
        System.out.println(LINE);
        System.out.println("Water:");
        System.out.print(LINE);
        int k = 1;
        for (int i = 0; i < logList.size(); i++) {
            if (logList.get(i).toString().startsWith("Water")) {
                System.out.println(k + ". " + logList.get(i).toString().substring(8));
                k++;
            }
        }
        System.out.println(LINE);
        System.out.println("Exercises:");
        System.out.print(LINE);
        int l = 1;
        for (int i = 0; i < logList.size(); i++) {
            if (logList.get(i).toString().startsWith("Exercise")) {
                System.out.print(l + ". " + logList.get(i).toString().substring(11));
                System.out.println("");
                l++;
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints a message summarizing a meal log entry, detailing the servings and calories.
     * @param logMeal the LogMeal object containing the log details
     */
    public static void printMealLogMessage(LogMeal logMeal) {
        System.out.println("You've logged " + Integer.toString(logMeal.getServings()) +
                " servings" + " of " + logMeal.getFoodName() + ".") ;
        System.out.println("Estimated calories: " + Integer.toString(logMeal.getMealCalories()) + " cal");
    }

    /**
     * Prints a message summarizing an exercise log entry, detailing the duration and calories burnt.
     * @param logExercise the LogExercise object containing the log details
     */
    public static void printExerciseLogMessage(LogExercise logExercise) {
        System.out.println("You've logged " + Integer.toString(logExercise.getDuration()) +
                " minutes" + " of " + logExercise.getExerciseName() + ".") ;
        System.out.println("Estimated calories burnt: " + Integer.toString(logExercise.getCaloriesBurnt()) + " cal");
    }

    /**
     * Displays the user's daily calorie and exercise summary, including net calories and
     * status against goals.
     */
    public static void printShowCalMessage() {
        int totalCalories = 0;
        int totalCaloriesFromMeals = 0;
        int totalCaloriesFromExercises = 0;
        int caloriegoal;
        String goal = "0";
        for (int i = 0; i < logList.size(); i++) {
            String[] parts = logList.get(i).toString().split(" ");
            int len = parts.length;
            String logString = logList.get(i).toString();
            int calIndex = -1;
            for (int j = 0; j < len; j++) {
                if (parts[j].equals("cal")) {
                    calIndex = j - 1; // Assuming calorie value is just before "kcal"
                    break;
                }
            }

            // Check if kcal index is found and the part at that index is a valid integer
            if (calIndex >= 0 && calIndex < parts.length) {
                String calorieString = parts[calIndex];
                if (calorieString.matches("\\d+")) { // Check if it's a valid integer
                    int calories = Integer.parseInt(calorieString);
                    if (logString.startsWith("Meal")) {
                        totalCaloriesFromMeals += calories;
                    } else if (logString.startsWith("Exercise")) {
                        totalCaloriesFromExercises += calories;
                    }
                } else {
                    System.out.println("Skipping non-integer calorie value: " + calorieString);
                }
            }
            if(logList.get(i).toString().startsWith("Goal")) {
                if (parts[1].equals("Calorie")) {
                    goal = parts[2].toString();
                }
            }
        }
        totalCalories = totalCaloriesFromMeals - totalCaloriesFromExercises;

        int totalSurplus = totalCalories - Integer.parseInt(goal);
        System.out.println("You have burned " + totalCaloriesFromExercises + " today!");
        System.out.println("You have consumed " + totalCaloriesFromMeals + " cal out of " + goal + " kcal");

        if(totalCaloriesFromMeals > Integer.parseInt(goal)) {
            System.out.println("You have exceeded your calorie intake goal!");
        } else{
            System.out.println("You're doing an excellent job of managing your calorie intake!");
        }
        if(totalSurplus > 0){
            System.out.println("Calorie surplus at the moment --> " + totalSurplus);
        } else{
            System.out.println("Calorie deficit at the moment --> " + -totalSurplus);
        }

    }

    /**
     * Prints a message confirming the logging of water intake.
     * @param newLogWater the LogWater object containing the water intake details
     */
    public static void printWaterLogMessage(LogWater newLogWater) {
        System.out.println("Successfully logged " + newLogWater.getQuantity() + " ml of water.");
    }

    /**
     * Prints a message summarizing the day's total water intake as a percentage of the daily goal.
     * @param totalWaterIntake the total amount of water consumed
     * @param waterGoal the daily water consumption goal
     */
    public static void printWaterIntakeMessage(int totalWaterIntake, int waterGoal) {
        double percentage = ((double) totalWaterIntake / waterGoal) * 100;
        System.out.println("Total water consumed today: " + totalWaterIntake +
                " ml (" + String.format("%.0f%%", percentage) + " of " + waterGoal + "ml goal).");
    }

    /**
     * Prints a message showing all the matching logs.
     * @param word the keyword to be search
     */
    public static void printMatchingLog (String word) {
        System.out.println(LINE + "Here are the matching logs in your list:");
        int matchingLogIndex = 1;
        boolean found = false;

        for (Log log : logList) {
            String logString = log.toString().trim(); // Trim the task string
            if (logString.startsWith("Meal") && logString.contains(word)) {
                System.out.print(matchingLogIndex + ". ");
                System.out.println(logString.substring(7));
                matchingLogIndex++;
                found = true;

            } else if (logString.startsWith("Water") && logString.contains(word)) {
                System.out.print(matchingLogIndex + ". ");
                System.out.println(logString.substring(8));
                matchingLogIndex++;
                found = true;
            } else if (logString.startsWith("Exercise") && logString.contains(word)) {
                System.out.print(matchingLogIndex + ". ");
                System.out.println(logString.substring(11));
                matchingLogIndex++;
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching logs found.");
        }

        System.out.println(LINE);
    }

    /**
     * Prints a message indicating that the delete request format is invalid.
     */
    public static void printInvalidDeleteFormatMessage() {
        System.out.println("This is an invalid request. Please try again!");
    }

    /**
     * Prints a message confirming the deletion of a task.
     * @param deletedLog The task that was deleted.
     */
    public static void printLogDeletedMessage(Log deletedLog) {
        System.out.println("Log deleted: " + deletedLog.toString());
    }

    /**
     * Prints a message to the console when a specified food item is not found in the database.
     * It guides the user on how to add the item to the database and log it.
     *
     * @param description The description of the food item.
     * @param servings The number of servings to log.
     */
    public static void printFoodItemNotFoundMessage(String description, int servings){
        System.out.println(description + " is not found in our food database.\n" +
                "Please enter the following command to add it to the database and log your meal.\n\n" +
                "add m/<FOOD> c/<CALORIES_PER_SERVING(cal)> s/<NUMBER_OF_SERVINGS>\n\n" +
                "Eg: 'add m/"+ description +" c/120 s/" + servings + " '\n\n" + "Remember to replace the" +
                " actual calories per serving value, if you are using the example command above."
        );
    }

    /**
     * Prints a message to the console when a specified exercise is not found in the database.
     * It provides instructions to the user on how to log the exercise.
     *
     * @param exerciseName The name of the exercise.
     * @param duration The duration of the exercise in minutes.
     */
    public static void printExerciseItemNotFoundMessage(String exerciseName, int duration){
        System.out.println(exerciseName + " is not found in our exercise database.\n" +
                "Please enter the following command to log your exercise.\n\n" +
                "add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN(cal)> s/<DURATION_IN_MIN>\n\n" +
                "Eg: 'add e/"+ exerciseName +" c/8 d/" +duration + " '\n\n" + "Remember to replace the" +
                " actual calories burnt per minute value, if you are using the example command above."
        );
    }


    /**
     * Notifies the user that a food item has been successfully added to the database and
     * that it is being logged.
     *
     * @param description The description of the food item that was added.
     */
    public static void printAddFoodItemMessage(String description){
        System.out.println(description + " has been added to the food database.\n" +
                "logging your meal.......\n"
        );
    }

    /**
     * Notifies the user that an exercise has been successfully added to the exercise database
     * and that it is being logged.
     *
     * @param exerciseName The name of the exercise that was added.
     */
    public static void printAddExerciseMessage(String exerciseName) {
        System.out.println(exerciseName + " has been added to the exercise database.\n" +
                "logging your exercise.......\n"
        );
    }

    /**
     * Prints a generic message indicating that the requested log was not found.
     */
    public static void printLogNotFoundMessage() {
        System.out.println("Log not found. View all logged entries using 'list'.");
    }

    /**
     * Informs the user that an invalid index was provided for a log entry, with guidelines
     * for correction.
     */
    public static void printInvalidItemIndexMessage(){
        System.out.println("Invalid log index. Please note the index should be 1 or above. \n" +
                "If you don't have multiple logs from the same name, index is set to 1 by default .");
    }

    /**
     * Informs the user about an invalid index used during a delete operation on meal logs,
     * providing usage instructions.
     */
    public static void printDeleteMealInvalidIndexMessage(){
        System.out.println("Invalid index. View all logged entries using 'list'." );
    }

    /**
     * Prints a detailed summary of daily activities including measurements, calorie intake, and exercise.
     * It provides a comprehensive overview of the user's daily health and fitness activities.
     *
     * @param height User's height in centimeters.
     * @param weight User's weight in kilograms.
     * @param BMI User's Body Mass Index.
     * @param totalCalories Total calories consumed.
     * @param totalWaterIntake Total water consumed in milliliters.
     * @param totalCaloriesBurnt Total calories burnt through exercises.
     * @param calorieGoal Daily calorie goal.
     * @param waterGoal Daily water intake goal.
     * @param netCalories Net calories after accounting for consumption and exercise.
     * @param calorieStatus Status message about calorie management.
     */
    public static void printShowSummaryMessage(int height, int weight, int BMI, int totalCalories,int
                                               totalWaterIntake, int totalCaloriesBurnt,
                                               String calorieGoal, String waterGoal,
                                               int netCalories, String calorieStatus) {
        System.out.println("Daily Summary:");
        System.out.println("Height: " + height + " cm");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("BMI: " + BMI);
        System.out.println("Total calories consumed: " + totalCalories + " cal");
        System.out.println("Total water consumed: " + totalWaterIntake + " ml");
        System.out.println("Total calories burnt: " + totalCaloriesBurnt + " cal");

        System.out.println("Calorie goal: " + calorieGoal + " cal");
        System.out.println("Water goal: " + waterGoal + " ml");

        System.out.println("Net calories: " + netCalories + " cal");
        System.out.println("Calorie status: " + calorieStatus);
    }

    /**
     * Informs the user that all logs have been cleared from the system.
     */
    public static void printAllLogsClearedMessage() {
        System.out.println("All logged data has been cleared.");
    }

    /**
     * Informs the user that there are no logs to clear as all have already been cleared.
     */
    public static void printDataAlreadyClearedMessage() {
        System.out.println("Logged data has already been cleared.");
    }

    /**
     * Prompts the user to log an exercise instead of adding it because it already exists.
     * @param exerciseName The name of the existing exercise.
     */

    public static void promptLogExerciseMessage(String exerciseName) {
        System.out.println("The exercise '" + exerciseName + " already exists. Please log " + exerciseName +
                " instead of adding it.");
    }

    /**
     * Prompts the user to log a food item instead of adding it because it already exists.
     * @param foodItemName The name of the existing food item.
     */
    public static void promptLogFoodMessage(String foodItemName) {
        System.out.println("The food item '" + foodItemName + "' already exists. Please log " + foodItemName +
                " instead of adding it.");
    }


    /**
     * Prints a warning message to the console indicating that the user has exceeded their
     * daily calorie intake goal. This message is a prompt for the user to adjust their dietary
     * intake to stay within health recommendations.
     */
    public static void printCalorieExceedingWarning() {
        System.out.println("WARNING: You are exceeding your calorie intake!");
    }

    /**
     * Prints a congratulatory message to the console indicating that the user has surpassed
     * their daily water intake goal.
     * This positive feedback is intended to motivate the user to continue good hydration practices.
     */
    public static void printWaterIntakeExceedingWarning() {
        System.out.println("Hooray! You have passed your water intake goal for the day!");
    }

    /**
     * Prints an error message to the console. This method provides a consistent format for
     * displaying error messages, making it easier for users to recognize and address issues
     * when they occur.
     *
     * @param errorMessage The specific error message to be displayed.
     */
    public static void printErrorMessage(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }
}
