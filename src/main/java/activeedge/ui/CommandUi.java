package activeedge.ui;

import static activeedge.log.LogList.logList;

import activeedge.log.Log;
import activeedge.log.LogExercise;
import activeedge.log.LogMeal;
import activeedge.log.LogWater;

import java.time.format.DateTimeFormatter;


public class CommandUi {

    static final String LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    public static void printWelcomeMessage() {
        String logo = "ACTIVE EDGE!";
        System.out.println("Welcome to " + logo);
        System.out.println("Take the next step in your Healthy Lifestyle!");
    }

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

    public static void printMealLogMessage(LogMeal logMeal) {
        System.out.println("You've logged " + Integer.toString(logMeal.getServings()) +
                " servings" + " of " + logMeal.getFoodName() + ".") ;
        System.out.println("Estimated calories: " + Integer.toString(logMeal.getMealCalories()) + " cal");
    }

    public static void printExerciseLogMessage(LogExercise logExercise) {
        System.out.println("You've logged " + Integer.toString(logExercise.getDuration()) +
                " minutes" + " of " + logExercise.getExerciseName() + ".") ;
        System.out.println("Estimated calories burnt: " + Integer.toString(logExercise.getCaloriesBurnt()) + " cal");
    }

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

    public static void printWaterLogMessage(LogWater newLogWater) {
        System.out.println("Successfully logged " + newLogWater.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake, int waterGoal) {
        double percentage = ((double) totalWaterIntake / waterGoal) * 100;
        System.out.println("Total water consumed today: " + totalWaterIntake +
                " ml (" + String.format("%.0f%%", percentage) + " of " + waterGoal + "ml goal).");
    }

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

    public static void printFoodItemNotFoundMessage(String description, int servings){
        System.out.println(description + " is not found in our food database.\n" +
                "Please enter the following command to add it to the database and log your meal.\n\n" +
                "add m/<FOOD> c/<CALORIES_PER_SERVING(cal)> s/<NUMBER_OF_SERVINGS>\n\n" +
                "Eg: 'add m/"+ description +" c/120 s/" + servings + " '\n\n" + "Remember to replace the" +
                " actual calories per serving value, if you are using the example command above."
        );
    }

    public static void printExerciseItemNotFoundMessage(String exerciseName, int duration){
        System.out.println(exerciseName + " is not found in our exercise database.\n" +
                "Please enter the following command to log your exercise.\n\n" +
                "add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN(cal)> s/<DURATION_IN_MIN>\n\n" +
                "Eg: 'add e/"+ exerciseName +" c/8 d/" +duration + " '\n\n" + "Remember to replace the" +
                " actual calories burnt per minute value, if you are using the example command above."
        );
    }


    public static void printAddFoodItemMessage(String description){
        System.out.println(description + " has been added to the food database.\n" +
                "logging your meal.......\n"
        );
    }

    public static void printAddExerciseMessage(String exerciseName) {
        System.out.println(exerciseName + " has been added to the exercise database.\n" +
                "logging your exercise.......\n"
        );
    }


    public static void printLogNotFoundMessage() {
        System.out.println("Log not found. View all logged entries using 'list'.");
    }

    public static void printWaterLogFoundFormatErrorMessage(int amount){
        System.out.println("Did you mean 'delete " + amount + "'" );
    }

    public static void printInvalidItemIndexMessage(){
        System.out.println("Invalid log index. Please note the index should be 1 or above. \n" +
                "If you don't have multiple logs from the same name, index is set to 1 by default .");
    }

    public static void printDeleteMealInvalidIndexMessage(){
        System.out.println("Invalid index. View all logged entries using 'list'." );
    }

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

    public static void printAllLogsClearedMessage() {
        System.out.println("All logged data has been cleared.");
    }
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


    public static void printCalorieExceedingWarning() {
        System.out.println("WARNING: You are exceeding your calorie intake!");
    }

    public static void printWaterIntakeExceedingWarning() {
        System.out.println("Hooray! You have passed your water intake goal for the day!");
    }


    public static void printErrorMessage(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }
}
