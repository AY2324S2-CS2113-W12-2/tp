package activeedge.parser;

import activeedge.ExerciseData;

import command.HelpCommand;
import command.LogWaterCommand;
import command.LogMealCommand;
import command.ListFullCommand;
import command.ShowCaloriesCommand;
import command.ViewWaterIntakeCommand;
import command.ShowGoalsCommand;
import command.FindCommand;
import command.DeleteLogCommand;
import command.ActiveEdgeException;
import command.LogExerciseCommand;
import command.ShowSummaryCommand;
import command.ClearCommand;
import command.AddFoodItemCommand;
import command.AddExerciseItemCommand;
import command.ChangeHeightCommand;
import command.ChangeWeightCommand;
import command.ChangeCalorieGoalCommand;
import command.ChangeWaterGoalCommand;

import activeedge.Storage;

import static activeedge.log.LogList.logList;
import static activeedge.FoodData.foodItems;
import static activeedge.ExerciseData.exercisesList;
import activeedge.FoodData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public void handleInput(String input) {
        input = input.toLowerCase();
        try {
            String[] inputSplit = input.trim().split(" ");
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String date = currentDateTime.format(dateFormatter);
            String time = currentDateTime.format(timeFormatter);
            if (inputSplit[0].equalsIgnoreCase("log")) {
                if (inputSplit.length == 1) {
                    System.out.println("Please specify what you wish to log: ");
                    System.out.println("1. 'log w/[QUANTITY_OF_WATER]' to log your water intake");
                    System.out.println("2. 'log m/[MEAL_NAME] s/[NUMBER_OF_SERVINGS]' to " +
                            "log your meals");
                    System.out.println("3. 'log e/[EXERCISE_NAME] d/[DURATION_OF_EXERCISE]' to " +
                            "log your exercises");
                    return;
                } else {
                    String parts = input.trim().substring(4);
                    String[] items = parts.split("/");
                    if (!items[0].trim().equals("w") && !items[0].trim().equals("m") && !items[0].trim().equals("e")) {
                        System.out.println("Invalid command. Please enter a valid 'log' command.");
                        return;
                    }
                    if (items[0].trim().equals("w")) {
                        if (items.length < 2 || items[1].isEmpty()) {
                            System.out.println("Invalid command. Please enter 'log w/[WATER_QUANTITY]'.");
                            System.out.println("For example, 'log w/300'. Enter 'help' for more information.");
                        } else {
                            String quantityString = items[1];
                            LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString, date, time);
                            logWaterCommand.execute();
                        }
                    } else if (items[0].trim().equals("m")) {
                        String[] logParts = input.trim().split("m/|s/");
                        int length = logParts.length;
                        assert length >= 3;
                        if (length >= 3) {
                            String description = logParts[1].trim();
                            try {
                                int servings = Integer.parseInt(logParts[2].trim());
                                if (servings != Double.parseDouble(logParts[2].trim()) || servings <= 0) {
                                    System.out.println("Servings must be a positive integer value.");
                                    return;
                                }
                                int calories = 0;
                                boolean isItemPresentInFoodData = false;

                                for (int i = 0; i < foodItems.length; i++) {
                                    if (foodItems[i][0].equals(description)) {
                                        calories = Integer.parseInt(foodItems[i][1]) * servings;
                                        isItemPresentInFoodData = true;
                                    }
                                }
                                LogMealCommand logMealCommand = new LogMealCommand(description, servings,
                                        calories, date, time, isItemPresentInFoodData);
                                logMealCommand.execute();
                            } catch (NumberFormatException e) {
                                System.out.println("Servings must be a positive integer value. Please try again.");
                            }
                        } else {
                            System.out.println("Invalid command. Please enter 'log m/[FOOD]" +
                                    " s/[NUMBER_OF_SERVINGS]'.");
                            System.out.println("For example, 'log m/chicken rice s/2'. " +
                                    "Enter 'help' for more information.");
                        }
                    } else if (items[0].trim().equals("e")) {
                        String[] logParts = input.trim().split("e/|d/");
                        int length = logParts.length;
                        assert length >= 3;
                        if (length >= 3) {
                            String exerciseName = logParts[1].trim();
                            try {
                                int duration = Integer.parseInt(logParts[2].trim());
                                if (duration <= 0) {
                                    System.out.println("Duration must be a positive integer value.");
                                    return;
                                }
                                int caloriesBurnt = 0;
                                boolean isItemPresentInExerciseData = false;

                                for (int i = 0; i < exercisesList.length; i++) {
                                    if (exercisesList[i][0].equals(exerciseName)) {
                                        caloriesBurnt = Integer.parseInt(exercisesList[i][1]) * duration;
                                        isItemPresentInExerciseData = true;
                                    }
                                }
                                LogExerciseCommand logExerciseCommand = new LogExerciseCommand(exerciseName, duration,
                                        caloriesBurnt, date, time, isItemPresentInExerciseData);
                                logExerciseCommand.execute();
                            } catch (NumberFormatException e) {
                                System.out.println("Duration must be a positive integer value. Please try again.");
                            }
                        } else {
                            System.out.println("Invalid command. Please enter 'log e/[EXERCISE]" +
                                    " D/[DURATION_IN_MINUTES]'.");
                            System.out.println("For example, 'log e/running d/10'. Enter 'help' for more information.");
                        }
                    }
                }
            } else if (inputSplit[0].trim().equalsIgnoreCase("list")) {
                if (logList.size() > 0) {
                    new ListFullCommand();
                } else {
                    System.out.println("There are no items in your list!");
                }
            } else if (inputSplit[0].trim().equalsIgnoreCase("show")) { //show calories, water, and goals
                inputSplit = input.trim().split(" ", 2);
                if (inputSplit.length == 1) {
                    System.out.println("Please specify what you wish to view: ");
                    System.out.println("1. 'show w' to display your current water intake");
                    System.out.println("2. 'show c' to display your current calories intake");
                    System.out.println("3. 'show g' to display your current goals");
                } else if (inputSplit[1].equalsIgnoreCase("c")) { //shows calorie
                    new ShowCaloriesCommand();
                } else if (inputSplit[1].trim().equalsIgnoreCase("w")) { //shows water
                    ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();
                    viewWaterIntakeCommand.execute();
                } else if (inputSplit[1].trim().equalsIgnoreCase("g")) {  //shows goals
                    ShowGoalsCommand showGoalsCommand = new ShowGoalsCommand();
                    showGoalsCommand.execute();
                } else {
                    System.out.println("These are the only show commands: ");
                    System.out.println("1. show w - displays water intake ");
                    System.out.println("2. show c - displays calorie intake");
                    System.out.println("3. show g - displays goals");
                }
            } else if (inputSplit[0].trim().equalsIgnoreCase("delete")) {
                DeleteLogCommand deleteCommand = new DeleteLogCommand(input);
                deleteCommand.execute();
            } else if(inputSplit[0].trim().equalsIgnoreCase("find")) {
                new FindCommand(input);
            } else if (input.trim().equalsIgnoreCase("summary")) {
                new ShowSummaryCommand().execute();
            } else if (input.trim().equalsIgnoreCase("help")) {
                new HelpCommand();
            } else if (input.trim().equalsIgnoreCase("FoodData")) {
                FoodData.printFood();
            } else if (input.trim().equalsIgnoreCase("ExerciseData")) {
                ExerciseData.printExercises();
            } else if(input.trim().equalsIgnoreCase("clear")) {
                ClearCommand clearCommand = new ClearCommand();
                clearCommand.execute();
            } else if (input.trim().startsWith("add m/")) {
                String[] logParts = input.trim().split("m/|c/|s/");
                int length = logParts.length;
                if (length >= 4) {
                    String description = logParts[1].trim();
                    try {
                        int servings = Integer.parseInt(logParts[3].trim());
                        int caloriesPerServing = Integer.parseInt(logParts[2].trim());
                        if (!input.trim().matches("add m/[^ ]+ c/\\d+ s/\\d+")) {
                            System.out.println("Warning: The input format is incorrect. Please enter " +
                                    "'add m/[MEAL_NAME] c/[CALORIES] s/[NUMBER_OF_SERVINGS]'.");
                            return; // Return from the method to avoid further processing
                        }
                        if(servings == 0 || caloriesPerServing == 0){
                            System.out.println("Please input a value above 0!");
                        } else {
                            AddFoodItemCommand addFoodItemCommand = new AddFoodItemCommand(description, servings,
                                    caloriesPerServing, date, time);
                            addFoodItemCommand.execute();
                        }
                    } catch(NumberFormatException e){
                        System.out.println("Servings and calories per serving must be an integer value. " +
                                "Please try again.");
                    }
                } else {
                    System.out.println("Invalid command. Please enter 'add m/[FOOD] c/[CALORIES_PER_SERVING(cal)]" +
                            " s/[NUMBER_OF_SERVINGS]'. Calories and servings must be a positive integer");
                    System.out.println("For example, 'add m/Pizza c/300 s/2'. Enter 'help' for more information.");
                }
            } else if (input.trim().startsWith("add e/")) {
                String[] logParts = input.trim().split("e/|c/|d/");
                int length = logParts.length;
                if (length >= 4) {
                    String description = logParts[1].trim();
                    try {
                        int caloriesBurntPerMinute = Integer.parseInt(logParts[2].trim());
                        int duration = Integer.parseInt(logParts[3].trim());

                        if (!input.trim().matches("add e/[^ ]+ c/\\d+ d/\\d+")) {
                            System.out.println("Warning: The input format is incorrect. Please enter " +
                                    "'add e/[EXERCISE_NAME] c/[CALORIES_BURNT_PER_MINUTE] d/[DURATION_IN_MINUTES]'.");
                            return; // Return from the method to avoid further processing
                        }

                        AddExerciseItemCommand addExerciseItemCommand = new AddExerciseItemCommand(description,
                                duration, caloriesBurntPerMinute, date, time);
                        addExerciseItemCommand.execute();
                    } catch(NumberFormatException e){
                        System.out.println("Duration and calories burnt per minute must be an integer value. " +
                                "Please try again.");
                    }
                } else {
                    System.out.println("Invalid command. Please enter 'add e/[EXERCISE_NAME] " +
                            "c/[CALORIES_BURNT_PER_MINUTE] d/[DURATION_IN_MINUTES]'. Calories burnt and duration " +
                            "must be positive integer");
                    System.out.println("For example, 'add e/Running c/10 d/30'. Enter 'help' for more information.");
                }
            } else if (inputSplit[0].trim().equalsIgnoreCase("change")) {
                inputSplit = input.trim().split(" ", 2);
                if (inputSplit.length == 1) {
                    System.out.println("Please specify what you want to change:");
                    System.out.println("1. 'change h' to change your height");
                    System.out.println("2. 'change w' to  change your weight");
                    System.out.println("3. 'change cg' to change calorie goal");
                    System.out.println("4. 'change wg' to change water goal");
                } else if (inputSplit[1].trim()equalsIgnoreCase("h")) {
                    ChangeHeightCommand.execute(); // Check if this requires arguments like new height
                } else if (inputSplit[1].trim().equalsIgnoreCase("w")) {
                    ChangeWeightCommand.execute();
                } else if (inputSplit[1].trim().equalsIgnoreCase("wg")) {
                    ChangeWaterGoalCommand.execute();
                } else if (inputSplit[1].trim().equalsIgnoreCase("cg")) {
                    ChangeCalorieGoalCommand.execute(); // Check if this requires arguments like new calorie goal
                } else {
                    System.out.println("These are the only change commands: ");
                    System.out.println("1. change h - change height");
                    System.out.println("2. change w - change weight");
                    System.out.println("3. change cg - change calorie goal");
                    System.out.println("4. change wg - change water goal");
                }
            }
            else {
                System.out.println("Unknown command.");
            }
            Storage.saveLogsToFile("data/data.txt");
        } catch (ActiveEdgeException e) {
            throw new RuntimeException(e);
        }
    }
}
