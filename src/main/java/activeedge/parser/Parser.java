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
import command.DeleteTaskCommand;
import command.ActiveEdgeException;
import command.LogExerciseCommand;
import command.ShowSummaryCommand;
import command.ClearCommand;
import command.AddFoodItemCommand;
import command.AddExerciseItemCommand;

import activeedge.Storage;

import static activeedge.task.TaskList.tasksList;
import static activeedge.FoodData.foodItems;
import static activeedge.ExerciseData.exercisesList;
import activeedge.FoodData;
import java.time.LocalDateTime;


public class    Parser {
    public void handleInput(String input) {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            if (input.contains("help")) {
                new HelpCommand();
            } else if (input.equalsIgnoreCase("FoodData")) {
                FoodData.printFood();
            }else if (input.equalsIgnoreCase("ExerciseData")) {
                ExerciseData.printExercises();
            } else if (input.startsWith("log")) {
                String parts = input.substring(4);
                String[] items = parts.split("/");
                if (items[0].equals("w")) {
                    String quantityString = items[1];
                    LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString, currentDateTime);
                    logWaterCommand.execute();
                } else if (items[0].equals("m")) {
                    String[] logParts = input.split("m/|s/");
                    int length = logParts.length;
                    assert length >= 3;
                    if(length >= 3) {
                        String description = logParts[1].trim();
                        try {
                            int servings = Integer.parseInt(logParts[2]);
                            if (servings != Double.parseDouble(logParts[2]) || servings <= 0) {
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
                                    calories, currentDateTime, isItemPresentInFoodData);
                            logMealCommand.execute();
                        } catch (NumberFormatException e) {
                            System.out.println("Servings must be a positive integer value. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid command. Please enter 'log m/[FOOD]" +
                                " s/[NUMBER_OF_SERVINGS]'.");
                        System.out.println("For example, 'log m/chicken rice s/2'. Enter 'help' for more information.");
                    }
                } else if (items[0].equals("e")){
                    String[] logParts = input.split("e/|d/");
                    int length = logParts.length;
                    assert length >= 3;
                    if(length >= 3) {
                        String exerciseName = logParts[1].trim();
                        try {
                            int duration = Integer.parseInt(logParts[2]);
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
                                    caloriesBurnt, currentDateTime, isItemPresentInExerciseData);
                            logExerciseCommand.execute();
                        } catch (NumberFormatException e) {
                            System.out.println("Duration must be a positive integer value. Please try again.");
                        }
                    }
                    else {
                        System.out.println("Invalid command. Please enter 'log e/[EXERCISE]" +
                                " D/[DURATION_IN_MINUTES]'.");
                        System.out.println("For example, 'log e/running d/10'. Enter 'help' for more information.");
                    }
                }

            } else if (input.startsWith("list")) {
                if (tasksList.size() > 0) {
                    new ListFullCommand();
                } else {
                    System.out.println("There are no items in your list!");
                }
            } else if (input.startsWith("show")) { //show calories, water, and goals
                String[] parts = input.split(" ");
                if(parts.length == 1){
                    System.out.println("Please specify what you wish to view: ");
                    System.out.println("1. 'show w' to view your current water intake");
                    System.out.println("2. 'show c' to view your current calories intake");
                } else if (parts[1].startsWith("c")) { //shows calorie
                    new ShowCaloriesCommand();
                } else if (parts[1].startsWith("w")) { //shows water
                    ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();
                    viewWaterIntakeCommand.execute();
                } else if (parts[1].startsWith("g")) {  //shows goals
                    ShowGoalsCommand showGoalsCommand = new ShowGoalsCommand();
                    showGoalsCommand.execute();
                } else {
                    System.out.println("Invalid command!\n");
                }
            } else if(input.startsWith("find")) {
                new FindCommand(input);
            } else if(input.startsWith("delete")){
                DeleteTaskCommand deleteCommand = new DeleteTaskCommand(input);
                deleteCommand.execute();
            } else if (input.startsWith("summary")) {
                new ShowSummaryCommand().execute();
            } else if(input.equalsIgnoreCase("clear")) {
                ClearCommand clearCommand = new ClearCommand();
                clearCommand.execute();
            } else if (input.startsWith("add m/")) {
                String[] logParts = input.split("m/|c/|s/");
                int length = logParts.length;
                if (length >= 4) {
                    String description = logParts[1].trim();
                    try {
                        int servings = Integer.parseInt(logParts[3].trim());
                        int caloriesPerServing = Integer.parseInt(logParts[2].trim());
                        if(servings == 0 || caloriesPerServing == 0){
                            System.out.println("Please input a value above 0!");
                        }
                        else {
                            AddFoodItemCommand addFoodItemCommand = new AddFoodItemCommand(description, servings,
                                    caloriesPerServing, currentDateTime);
                            addFoodItemCommand.execute();
                        }
                    } catch(NumberFormatException e){
                        System.out.println("Servings and calories per serving must be an integer value. " +
                                "Please try again.");
                    }
                } else {
                    System.out.println("Invalid command. Please enter 'add m/[FOOD] c/[CALORIES_PER_SERVING(kCal)]" +
                            " s/[NUMBER_OF_SERVINGS]'.");
                    System.out.println("For example, 'add m/Pizza c/300 s/2'. Enter 'help' for more information.");
                }
            } else if (input.startsWith("add e/")) {
                String[] logParts = input.split("e/|c/|d/");
                int length = logParts.length;
                if (length >= 4) {
                    String description = logParts[1].trim();
                    try {
                        int caloriesBurntPerMinute = Integer.parseInt(logParts[2].trim());
                        int duration = Integer.parseInt(logParts[3].trim());

                        AddExerciseItemCommand addExerciseItemCommand = new AddExerciseItemCommand(description,
                                duration, caloriesBurntPerMinute, currentDateTime);
                        addExerciseItemCommand.execute();
                    } catch(NumberFormatException e){
                        System.out.println("Duration and calories burnt per minute must be an integer value. " +
                                "Please try again.");
                    }
                } else {
                    System.out.println("Invalid command. Please enter 'add e/[EXERCISE_NAME] " +
                            "c/[CALORIES_BURNT_PER_MINUTE] d/[DURATION_IN_MINUTES]'.");
                    System.out.println("For example, 'add e/Running c/10 d/30'. Enter 'help' for more information.");
                }
            } else {
                System.out.println("Unknown command.");
            }
            Storage.saveLogsToFile("data/data.txt");
        } catch (ActiveEdgeException e) {
            throw new RuntimeException(e);
        }
    }
}
