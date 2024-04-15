package activeedge.parser;

import command.Command;
import command.ListFullCommand;
import command.DeleteLogCommand;
import command.FindCommand;
import command.ShowSummaryCommand;
import command.HelpCommand;
import command.PrintFoodCommand;
import command.PrintExercisesCommand;
import command.ClearCommand;
import command.InvalidCommand;
import command.LogWaterCommand;
import command.LogMealCommand;
import command.LogExerciseCommand;
import command.ShowCaloriesCommand;
import command.ViewWaterIntakeCommand;
import command.ShowGoalsCommand;
import command.AddFoodItemCommand;
import command.AddExerciseItemCommand;
import command.ChangeHeightCommand;
import command.ChangeWeightCommand;
import command.ChangeWaterGoalCommand;
import command.ChangeCalorieGoalCommand;
import static activeedge.FoodData.foodItems;
import static activeedge.ExerciseData.exercisesList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input into commands.
 */
public class Parser {

    /**
     * Handles user input and returns the corresponding command.
     *
     * @param input The user input string.
     * @return The command corresponding to the input.
     */
    public Command handleInput(String input) {
        input = input.toLowerCase();
        String[] inputSplit = input.trim().split(" ");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        if (inputSplit[0].equalsIgnoreCase("log")) {
            return parseLogCommand(input, date, time);
        } else if (inputSplit[0].trim().equalsIgnoreCase("list") && inputSplit.length == 1 ) {
            return new ListFullCommand();
        } else if (inputSplit[0].trim().equalsIgnoreCase("show")) {
            return parseShowCommand(input);
        } else if (inputSplit[0].trim().equalsIgnoreCase("delete")) {
            return new DeleteLogCommand(input);
        } else if (inputSplit[0].trim().equalsIgnoreCase("find")) {
            return new FindCommand(input);
        } else if (input.trim().equalsIgnoreCase("summary")) {
            return new ShowSummaryCommand();
        } else if (input.trim().equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (input.trim().equalsIgnoreCase("FoodData")) {
            return new PrintFoodCommand();
        } else if (input.trim().equalsIgnoreCase("ExerciseData")) {
            return new PrintExercisesCommand();
        } else if (input.trim().equalsIgnoreCase("clear")) {
            return new ClearCommand();
        } else if (input.contains("add")) {
            return parseAddCommand(input, date, time); // Call parseAddCommand
        } else if (inputSplit[0].trim().equalsIgnoreCase("change")) {
            return parseChangeCommand(input);
        } else {
            return new InvalidCommand("Unknown command. Please enter 'help' to see all the commands.");
        }
    }

    private Command parseLogCommand(String input, String date, String time) {
        String[] inputSplit = input.trim().split(" ");
        if (inputSplit.length == 1 ) {
            return new InvalidCommand("Please specify what you wish to log:\n" +
                    "1. 'log w/[QUANTITY_OF_WATER]' to log your water intake\n" +
                    "2. 'log m/[MEAL_NAME] s/[NUMBER_OF_SERVINGS]' to log your meals\n" +
                    "3. 'log e/[EXERCISE_NAME] d/[DURATION_OF_EXERCISE]' to log your exercises");
        } else {
            String parts = input.trim().substring(4);
            String[] items = parts.split("/");
            if (items[0].trim().equals("w")) {
                return parseWaterLogCommand(input, date, time);
            } else if (items[0].trim().equals("m")) {
                return parseMealLogCommand(input, date, time);
            } else if (items[0].trim().equals("e")) {
                return parseExerciseLogCommand(input, date, time);
            } else {
                return new InvalidCommand("Invalid command. Please enter a valid 'log' command.");
            }
        }
    }

    private Command parseWaterLogCommand(String input, String date, String time) {
        try {
            // Normalize input to remove excessive whitespaces around and within the command
            input = input.replaceAll("\\s+/\\s+", "/").trim();  // Normalize spaces around slashes
            input = input.replaceAll("\\s+", " ");  // Normalize spaces elsewhere

            // Attempt to split the command based on the presence of "w/"
            String[] commandParts = input.split("\\s+w/");

            // Check if the command format is incorrect or the quantity is missing
            if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
                return new InvalidCommand("Invalid command. Please enter 'log w/[WATER_QUANTITY]'. " +
                        "For example, 'log w/300'. Enter 'help' for more information.");
            }

            // Extract the quantity and validate it
            String quantityString = commandParts[1].trim();
            if (!quantityString.matches("\\d+")) {  // Ensure the quantity is a positive number
                return new InvalidCommand("Water quantity must be a positive integer. " +
                        "Please input an integer above 0!");
            }

            // Parse the quantity and check the limits
            int quantity = Integer.parseInt(quantityString);
            if (quantity <= 0) {
                return new InvalidCommand("The water quantity must be above 0. Please input a valid integer.");
            } else if (quantity > 6000) {
                return new InvalidCommand("Please enter a water value that is 6000ml or lesser.");
            }

            // Return a valid command if all checks are passed
            return new LogWaterCommand(Integer.toString(quantity), date, time);
        } catch(NumberFormatException e){
            return new InvalidCommand("Please enter a water value that is 6000ml or lesser.");
        }
    }



    private Command parseMealLogCommand(String input, String date, String time) {
        input = input.replaceAll("\\s+/\\s+", "/").trim();  // Normalize spaces around slashes
        input = input.replaceAll("\\s+", " ");  // Normalize spaces elsewhere

        String[] logParts = input.trim().replace("/", " ").replaceAll("\\s+", " ").
                split(" ");
        int length = logParts.length;
        assert length >= 5;
        String description = "";
        if (length >= 5) {
            for (int i = 2; i <= length - 3; i++) {
                if (i < length - 3) {
                    description = description + logParts[i].trim() + " ";
                } else {
                    description = description + logParts[i].trim();
                }
            }
            if (!description.matches("[a-zA-Z0-9 \\-]*")) {
                return new InvalidCommand("Meal name must not contain symbols.");
            }
            try {
                int servings = Integer.parseInt(logParts[length-1].trim());
                if (logParts[length-1].equalsIgnoreCase("s")) {
                    return new InvalidCommand("Number of servings must not be empty!");
                } else if (servings != Double.parseDouble(logParts[length-1].trim()) || servings <= 0) {
                    return new InvalidCommand("Servings must be a positive integer value. " +
                            "Please try again.");
                } else if (servings > 10) {
                    return new InvalidCommand("Please re-enter a value that is 10 or below!\n" +
                            "If you wish to enter a value of more than 10, please do log in\n" +
                            "these meals twice by having the 2 different serving " +
                            "values add up to your intended value.");
                }
                int mealCalories = 0;
                boolean isItemPresentInFoodData = false;

                for (int i = 0; i < foodItems.length; i++) {
                    if (foodItems[i][0].equals(description)) {
                        mealCalories = Integer.parseInt(foodItems[i][1]) * servings;
                        isItemPresentInFoodData = true;
                    }
                }
                return new LogMealCommand(description, servings, mealCalories, date, time, isItemPresentInFoodData);
            } catch (NumberFormatException e) {
                return new InvalidCommand("Servings must be a positive integer value and 10 or lesser. " +
                        "Please try again.");
            }
        } else {
            if (length == 4 && logParts[2].equalsIgnoreCase("s")) {
                return new InvalidCommand("Meal name must not be empty!");
            } else if (logParts[length-1].equalsIgnoreCase("s")) {
                return new InvalidCommand("Number of servings must not be empty!");
            } else {
                return new InvalidCommand("Invalid command. Please enter " +
                        "'log m/[FOOD] s/[NUMBER_OF_SERVINGS]'. " +
                        "\"For example, 'log m/chicken rice s/2'. \" +\n" +
                        " \"Enter 'help' for more information.\"");
            }
        }
    }

    private Command parseExerciseLogCommand(String input, String date, String time) {
        input = input.replaceAll("\\s+/\\s+", "/").trim();  // Normalize spaces around slashes
        input = input.replaceAll("\\s+", " ");  // Normalize spaces elsewhere

        String[] logParts = input.trim().replace("/", " ").replaceAll("\\s+", " ").
                split(" ");
        int length = logParts.length;
        assert length >= 5;
        String exerciseName = "";
        if (length >= 5) {
            for (int i = 2; i <= length - 3; i++) {
                if (i < length - 3) {
                    exerciseName = exerciseName + logParts[i].trim() + " ";
                } else {
                    exerciseName = exerciseName + logParts[i].trim();
                }
            }
            if (!exerciseName.matches("[a-zA-Z0-9 \\-]*")) {
                return new InvalidCommand("Exercise name must not contain symbols.");
            }
            try {
                int duration = Integer.parseInt(logParts[length-1].trim());
                if (logParts[length-1].equalsIgnoreCase("d")) {
                    return new InvalidCommand("Duration cannot be empty!");
                } else if (duration != Double.parseDouble(logParts[length-1].trim()) || duration <= 0) {
                    return new InvalidCommand("Duration must be a positive integer value. " +
                            "Please try again.");
                } else if (duration > 200) {
                    return new InvalidCommand("Please re-enter a value that is 200 or below!\n" +
                            "If you wish to enter a value of more than 200, please do log in\n" +
                            "these exercises twice by having the 2 different duration " +
                            "values add up to your intended value.");
                }

                int caloriesBurnt = 0;
                boolean isItemPresentInExerciseData = false;

                for (String[] exercise : exercisesList) {
                    if (exercise[0].equalsIgnoreCase(exerciseName)) {
                        caloriesBurnt = Integer.parseInt(exercise[1]) * duration;
                        isItemPresentInExerciseData = true;
                        break;
                    }
                }
                return new LogExerciseCommand(exerciseName, duration, caloriesBurnt, date, time,
                        isItemPresentInExerciseData);
            } catch (NumberFormatException e) {
                return new InvalidCommand("Duration must be a positive integer value. " +
                        "Please try again.");
            }
        } else {
            if (length == 4 && logParts[2].equalsIgnoreCase("d")) {
                return new InvalidCommand("Exercise name must not be empty!");
            } else if (logParts[length-1].equalsIgnoreCase("d")) {
                return new InvalidCommand("Duration must not be empty!");
            } else {
                return new InvalidCommand("Invalid command. Please enter " +
                        "'log e/[EXERCISE] D/[DURATION_IN_MINUTES]'. " +
                        "\"For example, 'log e/running d/10'. Enter 'help' for more information.\"");
            }
        }
    }

    private Command parseShowCommand(String input) {
        String[] inputSplit = input.trim().split(" ", 2);
        if (inputSplit.length == 1) {
            return new InvalidCommand("Please specify what you wish to view:\n" +
                    "1. 'show w' to display your current water intake\n" +
                    "2. 'show c' to display your current calories intake\n" +
                    "3. 'show g' to display your current goals");
        } else if (inputSplit[1].equalsIgnoreCase("c")) {
            return new ShowCaloriesCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("w")) {
            return new ViewWaterIntakeCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("g")) {
            return new ShowGoalsCommand();
        } else {
            return new InvalidCommand("Invalid command. Please enter a valid 'show' command.");
        }
    }

    public Command parseAddCommand(String input, String date, String time) {
        input = input.replaceAll("\\s+/\\s+", "/").trim();  // Normalize spaces around slashes
        input = input.replaceAll("\\s+", " ");  // Normalize spaces elsewhere

        String[] logParts = input.trim().replace("/", " ").replaceAll("\\s+", " ").
                split(" ");
        if (logParts[0].equalsIgnoreCase("add") && logParts[1].equalsIgnoreCase("m")) {
            return parseAddMealCommand(input, date, time);
        } else if (logParts[0].equalsIgnoreCase("add") &&
                logParts[1].equalsIgnoreCase("e")) {
            return parseAddExerciseCommand(input, date, time);
        } else {
            return new InvalidCommand("Invalid add command. Type 'help' to see the two valid add commands.");
        }
    }

    private Command parseAddMealCommand(String input, String date, String time) {
        input = input.replaceAll("\\s+/\\s+", "/").trim();  // Normalize spaces around slashes
        input = input.replaceAll("\\s+", " ");  // Normalize spaces elsewhere

        String[] logParts = input.trim().replace("/", " ").replaceAll("\\s+", " ").
                split(" ");
        int length = logParts.length;
        assert length >= 7;
        String description = "";
        if (length >= 7) {
            for (int i = 2; i <= length - 5; i++) {
                if (i < length - 5) {
                    description = description + logParts[i].trim() + " ";
                } else {
                    description = description + logParts[i].trim();
                }
            }
            if (!description.matches("[a-zA-Z0-9 \\-]*")) {
                return new InvalidCommand("Meal name must not contain symbols.");
            }
            try {
                int servings = Integer.parseInt(logParts[length-1].trim());
                int caloriesPerServing = Integer.parseInt(logParts[length-3].trim());
                if (logParts[length-1].equalsIgnoreCase("s") &&
                        logParts[length-2].equalsIgnoreCase("m")){
                    return new InvalidCommand("Calories per serving and " +
                            "number of servings cannot be empty!" );
                } else if (servings != Double.parseDouble(logParts[length-1].trim()) || servings <= 0) {
                    return new InvalidCommand("Servings must be a positive integer value.");
                } else if (caloriesPerServing != Double.parseDouble(logParts[length-3].trim())
                        || caloriesPerServing <= 0) {
                    return new InvalidCommand("Calories per serving must be a positive integer value.");
                } else if ((servings != Double.parseDouble(logParts[length-1].trim()) || servings <= 0) &&
                        (caloriesPerServing != Double.parseDouble(logParts[length-3].trim())
                                || caloriesPerServing <= 0)) {
                    return new InvalidCommand("Calories per serving and " +
                            "number of servings must be a positive integer value.");
                } else if (servings > 10) {
                    return new InvalidCommand("Please re-enter a value that is 10 or below!\n" +
                            "If you wish to enter a value of more than 10, please do log in\n" +
                            "these meals twice by having the 2 different serving " +
                            "values add up to your intended value.");
                } else if (description.length() > 32) {
                    return new InvalidCommand("Please re-enter a food name that is within 32 characters!");
                } else {
                    return new AddFoodItemCommand(description, servings, caloriesPerServing, date, time);
                }
            } catch (NumberFormatException e) {
                if (logParts[length-1].equalsIgnoreCase("s") && !logParts[length-2].
                        equalsIgnoreCase("c")) {
                    return new InvalidCommand("Number of servings cannot be empty!");
                } else if (logParts[length-3].equalsIgnoreCase("c") && logParts[length-2].
                        equalsIgnoreCase("s")) {
                    return new InvalidCommand("Calories per serving cannot be empty. " +
                            "Please input a integer above 0!");
                } else {
                    return new InvalidCommand("Servings and calories per serving must be an integer " +
                            "value. Servings need to be 10 or lesser. Please try again.");
                }
            }
        } else {
            if (length == 6 && logParts[2].equalsIgnoreCase("c")) {
                return new InvalidCommand("Meal name must not be empty!");
            } else if (length == 6 && logParts[length-3].equalsIgnoreCase("c")) {
                return new InvalidCommand("Calories per serving cannot be empty!");
            } else if (logParts[length-1].equalsIgnoreCase("s")) {
                return new InvalidCommand("Number of servings cannot be empty!");
            } else {
                return new InvalidCommand("Invalid command format.");
            }
        }
    }

    private Command parseAddExerciseCommand(String input, String date, String time) {
        input = input.replaceAll("\\s+/\\s+", "/").trim();  // Normalize spaces around slashes
        input = input.replaceAll("\\s+", " ");  // Normalize spaces elsewhere

        String[] logParts = input.trim().replace("/", " ").replaceAll("\\s+", " ").
                split(" ");
        int length = logParts.length;
        assert length >= 7;
        String description = "";
        if (length >= 7) {
            for (int i = 2; i <= length - 5; i++) {
                if (i < length - 5) {
                    description = description + logParts[i].trim() + " ";
                } else {
                    description = description + logParts[i].trim();
                }
            }
            if (!description.matches("[a-zA-Z0-9 \\-]*")) {
                return new InvalidCommand("Exercise name must not contain symbols.");
            }
            try {
                int caloriesBurntPerMinute = Integer.parseInt(logParts[length-3].trim());
                int duration = Integer.parseInt(logParts[length-1].trim());
                if (logParts[length-1].equalsIgnoreCase("s") &&
                        logParts[length-2].equalsIgnoreCase("m")){
                    return new InvalidCommand("Calories burnt per min and " +
                            "duration cannot be empty!" );
                } else if (duration != Double.parseDouble(logParts[length-1].trim()) || duration <= 0) {
                    return new InvalidCommand("Duration must be a positive integer value.");
                } else if (caloriesBurntPerMinute != Double.parseDouble(logParts[length-3].trim())
                        || caloriesBurntPerMinute <= 0) {
                    return new InvalidCommand("Calories burnt per min must be a positive" +
                            " integer value.");
                } else if ((duration != Double.parseDouble(logParts[length-1].trim()) || duration <= 0) &&
                        (caloriesBurntPerMinute != Double.parseDouble(logParts[length-3].trim())
                                || caloriesBurntPerMinute <= 0)) {
                    return new InvalidCommand("Calories per serving and " +
                            "number of servings must be a positive integer value.");
                } else if (duration > 200) {
                    return new InvalidCommand("Please re-enter a value that is 200 or below!\n" +
                            "If you wish to enter a value of more than 200, please do log in\n" +
                            "these exercises twice by having the 2 different duration " +
                            "values add up to your intended value.");
                } else if (description.length() > 32) {
                    return new InvalidCommand("Please re-enter an exercise name that is within 32 characters!");
                } else {
                    return new AddExerciseItemCommand(description, duration, caloriesBurntPerMinute, date, time);
                }
            } catch (NumberFormatException e) {
                if (logParts[length-1].equalsIgnoreCase("d") && !logParts[length-2].
                        equalsIgnoreCase("c")) {
                    return new InvalidCommand("Duration cannot be empty!");
                } else if (logParts[length-3].equalsIgnoreCase("c") && logParts[length-2].
                        equalsIgnoreCase("d")) {
                    return new InvalidCommand("Calories burnt per minute cannot be empty. " +
                            "Please input a integer above 0!");
                } else {
                    return new InvalidCommand("Duration and calories burnt per minute must be an " +
                            "integer value. Please try again.");
                }
            }
        } else {
            if (length == 6 && logParts[2].equalsIgnoreCase("c")) {
                return new InvalidCommand("Exercise name must not be empty!");
            } else if (length == 6 && logParts[length-3].equalsIgnoreCase("c")) {
                return new InvalidCommand("Calories burnt per min cannot be empty!");
            } else if (logParts[length-1].equalsIgnoreCase("d")) {
                return new InvalidCommand("Duration cannot be empty!");
            } else {
                return new InvalidCommand("Invalid command format.");
            }
        }
    }

    private Command parseChangeCommand(String input) {
        String[] inputSplit = input.trim().split(" ", 2);
        if (inputSplit.length == 1) {
            return new InvalidCommand("Please specify what you want to change:\n" +
                    "1. 'change h' - change your height\n" +
                    "2. 'change w' - change your weight\n" +
                    "3. 'change cg' - change calorie goal\n" +
                    "4. 'change wg' - change water goal");
        } else if (inputSplit[1].trim().equalsIgnoreCase("g")) {
            return new InvalidCommand("Please specify which goal you want to change:\n" +
                    "1. 'change cg' - change calorie goal\n" +
                    "2. 'change wg' - change water goal");
        } else if (inputSplit[1].trim().equalsIgnoreCase("h")) {
            return new ChangeHeightCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("w")) {
            return new ChangeWeightCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("wg")) {
            return new ChangeWaterGoalCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("cg")) {
            return new ChangeCalorieGoalCommand();
        } else {
            return new InvalidCommand("Invalid command. Please enter a valid 'change' command.");
        }
    }
}
