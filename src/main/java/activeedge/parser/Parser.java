package activeedge.parser;

import activeedge.Storage;
import command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static activeedge.ExerciseData.exercisesList;
import static activeedge.FoodData.foodItems;

public class Parser {
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
        } else if (inputSplit[0].trim().equalsIgnoreCase("list")) {
            return new ListFullCommand();
        } else if (inputSplit[0].trim().equalsIgnoreCase("show")) {
            return parseShowCommand(input);
        } else if (inputSplit[0].trim().equalsIgnoreCase("delete")) {
            return new DeleteTaskCommand(input);
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
        } else if (input.trim().startsWith("add m/")) {
            return parseAddMealCommand(input, date, time);
        } else if (input.trim().startsWith("add e/")) {
            return parseAddExerciseCommand(input, date, time);
        } else if (inputSplit[0].trim().equalsIgnoreCase("change")) {
            return parseChangeCommand(input);
        } else {
            System.out.println("Unknown command.");
        }
        Storage.saveLogsToFile("data/data.txt");
        return null;
    }

    private Command parseLogCommand(String input, String date, String time) {
        String[] inputSplit = input.trim().split(" ");
        if (inputSplit.length == 1) {
            System.out.println("Please specify what you wish to log:");
            System.out.println("1. 'log w/[QUANTITY_OF_WATER]' to log your water intake");
            System.out.println("2. 'log m/[MEAL_NAME] s/[NUMBER_OF_SERVINGS]' to log your meals");
            System.out.println("3. 'log e/[EXERCISE_NAME] d/[DURATION_OF_EXERCISE]' to log your exercises");
        } else {
            String parts = input.trim().substring(4);
            String[] items = parts.split("/");
            if (!items[0].trim().equals("w") && !items[0].trim().equals("m") && !items[0].trim().equals("e")) {
                return new InvalidCommand("Invalid command. Please enter a valid 'log' command.");
            }
            if (items[0].trim().equals("w")) {
                return parseWaterLogCommand(items, date, time);
            } else if (items[0].trim().equals("m")) {
                return parseMealLogCommand(input, date, time);
            } else {
                return parseExerciseLogCommand(input, items, date, time);
            }
        }
        return null;
    }

    private Command parseWaterLogCommand(String[] items, String date, String time) {
        if (items.length < 2 || items[1].isEmpty()) {
            return new InvalidCommand("Invalid command. Please enter 'log w/[WATER_QUANTITY]'. " +
                    "\"For example, 'log w/300'. Enter 'help' for more information.\"");
        } else {
            String quantityString = items[1];
            return new LogWaterCommand(quantityString, date, time);
        }
    }

    private Command parseMealLogCommand(String input, String date, String time) {
        String[] logParts = input.trim().split("m/|s/");
        int length = logParts.length;
        assert length >= 3;
        if (length >= 3) {
            String description = logParts[1].trim();
            try {
                int servings = Integer.parseInt(logParts[2].trim());
                if (servings != Double.parseDouble(logParts[2].trim()) || servings <= 0) {
                    return new InvalidCommand("Servings must be a positive integer value.");
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
                return new InvalidCommand("Servings must be a positive integer value. Please try again.");
            }
        } else {
            return new InvalidCommand("Invalid command. Please enter " +
                    "'log m/[FOOD] s/[NUMBER_OF_SERVINGS]'. " +
                    "\"For example, 'log m/chicken rice s/2'. \" +\n" +
                    " \"Enter 'help' for more information.\"");
        }
    }

    private Command parseExerciseLogCommand(String input, String[] items, String date, String time) {
        String[] logParts = input.trim().split("e/|d/");
        int length = logParts.length;
        assert length >= 3;
        if (length >= 3) {
            String exerciseName = logParts[1].trim();
            try {
                int duration = Integer.parseInt(logParts[2].trim());
                if (duration <= 0) {
                    return new InvalidCommand("Duration must be a positive integer value.");
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
                return new InvalidCommand("Duration must be a positive integer value. Please try again.");
            }
        } else {
            return new InvalidCommand("Invalid command. Please enter " +
                    "'log e/[EXERCISE] D/[DURATION_IN_MINUTES]'. " +
                    "\"For example, 'log e/running d/10'. Enter 'help' for more information.\"");
        }
    }

    private Command parseShowCommand(String input) {
        String[] inputSplit = input.trim().split(" ", 2);
        if (inputSplit.length == 1) {
            System.out.println("Please specify what you wish to view:");
            System.out.println("1. 'show w' to display your current water intake");
            System.out.println("2. 'show c' to display your current calories intake");
            System.out.println("3. 'show g' to display your current goals");
        } else if (inputSplit[1].equalsIgnoreCase("c")) {
            return new ShowCaloriesCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("w")) {
            return new ViewWaterIntakeCommand();
        } else if (inputSplit[1].trim().equalsIgnoreCase("g")) {
            return new ShowGoalsCommand();
        } else {
            return new InvalidCommand("Invalid command. Please enter a valid 'show' command.");
        }
        return null;
    }

    private Command parseAddMealCommand(String input, String date, String time) {
        String[] logParts = input.trim().split("m/|c/|s/");
        int length = logParts.length;
        if (length >= 4) {
            String description = logParts[1].trim();
            try {
                int servings = Integer.parseInt(logParts[3].trim());
                int caloriesPerServing = Integer.parseInt(logParts[2].trim());
                if (!input.trim().matches("add m/[^ ]+ c/\\d+ s/\\d+")) {
                    return new InvalidCommand("Invalid command format.");
                }
                if (servings == 0 || caloriesPerServing == 0) {
                    return new InvalidCommand("Please input a value above 0!");
                } else {
                    return new AddFoodItemCommand(description, servings, caloriesPerServing, date, time);
                }
            } catch(NumberFormatException e){
                return new InvalidCommand("Servings and calories per serving must be an integer value. Please try again.");
            }
        } else {
            return new InvalidCommand("Invalid command format.");
        }
    }

    private Command parseAddExerciseCommand(String input, String date, String time) {
        String[] logParts = input.trim().split("e/|c/|d/");
        int length = logParts.length;
        if (length >= 4) {
            String description = logParts[1].trim();
            try {
                int caloriesBurntPerMinute = Integer.parseInt(logParts[2].trim());
                int duration = Integer.parseInt(logParts[3].trim());

                if (!input.trim().matches("add e/[^ ]+ c/\\d+ d/\\d+")) {
                    return new InvalidCommand("Invalid command format.");
                }

                return new AddExerciseItemCommand(description, duration, caloriesBurntPerMinute, date, time);
            } catch(NumberFormatException e){
                return new InvalidCommand("Duration and calories burnt per minute must be an integer value. Please try again.");
            }
        } else {
            return new InvalidCommand("Invalid command format.");
        }
    }

    private Command parseChangeCommand(String input) {
        String[] inputSplit = input.trim().split(" ", 2);
        if (inputSplit.length == 1) {
            System.out.println("Please specify what you want to change:");
            System.out.println("1. 'change h' - change your height");
            System.out.println("2. 'change w' - change your weight");
            System.out.println("3. 'change cg' - change calorie goal");
            System.out.println("4. 'change wg' - change water goal");
        } else if (inputSplit[1].trim().equalsIgnoreCase("g")) {
            System.out.println("Please specify which goal you want to change:");
            System.out.println("1. 'change cg' - change calorie goal");
            System.out.println("2. 'change wg' - change water goal");
        } else if (inputSplit[1].trim().equalsIgnoreCase("h")){
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
        return null;
    }
}
