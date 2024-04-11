package activeedge;

import activeedge.log.LogGoals;
import activeedge.log.LogMeal;
import activeedge.log.LogList;
import activeedge.log.LogExercise;
import activeedge.log.LogWater;
import activeedge.userdetails.LogBMI;
import activeedge.userdetails.LogHeight;
import activeedge.userdetails.LogWeight;
import activeedge.userdetails.UserDetailsList;
import command.AddBMICommand;
import command.AddHeightCommand;
import command.AddWeightCommand;
import command.AddCalorieGoalCommand;
import command.AddWaterGoalCommand;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * The {@code Storage} class handles file operations for the Health Tracker application.
 * It includes methods for ensuring directory existence, creating files, saving logs to files,
 * and fetching data from files.
 */
public class Storage {
    /**
     * Ensures that the directory for a given file path exists.
     * If the directory does not exist, it creates all necessary parent directories.
     *
     * @param filePath The file path for which to ensure directory existence.
     */
    public static void ensureDirectoryExists(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Creates a new file at the specified file path.
     * If the file's directory does not exist, it ensures the creation of the directory structure.
     *
     * @param filePath The path of the file to be created.
     */
    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the current logs from {@code LogList} to a file at the given file path.
     * Each log is converted to a string and written to the file, one log per line.
     *
     * @param filePath The path of the file where logs should be saved.
     */
    public static void saveLogsToFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            for (int i = 0; i < UserDetailsList.detailsList.size(); i++) {
                String out = UserDetailsList.detailsList.get(i).toString();
                fw.write(out + "\n");
            }
            for (int i = 0; i < LogList.logList.size(); i++) {
                String out = LogList.logList.get(i).toString();
                fw.write(out + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving logs to file: " + e.getMessage());
        }
    }

    public static void listEmpty() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);
        System.out.print("\n");
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        System.out.println("Since you are new here, let's start with your height and weight " +
                "to set things up!");
        Scanner scanner = new Scanner(System.in); // Create Scanner object outside the loop

        try {
            int heightInput = 0;
            int weightInput = 0;
            int calorieGoal = 0;
            int waterGoal = 0;
            while (j < 1) {
                System.out.println("Please input your height (in cm): ");
                try {
                    heightInput = Integer.parseInt(scanner.nextLine());
                    if (heightInput >= 50 && heightInput <= 300) {
                        AddHeightCommand addHeightCommand = new
                                AddHeightCommand(heightInput, date, time);
                        addHeightCommand.execute();
                        saveLogsToFile("data/data.txt");
                        j++;
                    } else {
                        System.out.println("Please input a whole number between 50 and 300!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input a whole number between 50 and 300!");
                }
            }
            while (i < 1) {
                System.out.println("Please input your weight (in kg): ");
                try {
                    weightInput = Integer.parseInt(scanner.nextLine());
                    if (weightInput >= 1 && weightInput <= 700) {
                        AddWeightCommand addWeightCommand = new
                                AddWeightCommand(weightInput, date, time);
                        addWeightCommand.execute();
                        saveLogsToFile("data/data.txt");
                        i++;
                    } else {
                        System.out.println("Please input a whole number between 1 and 700!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input a whole number between 1 and 700!");
                }
            }

            AddBMICommand addBMICommand = new AddBMICommand(heightInput, weightInput, date, time);
            addBMICommand.execute();
            saveLogsToFile("data/data.txt");

            // Prompt for setting daily calorie goal
            while (k < 1) {
                System.out.println("Please set your daily calorie goal (in cal): ");
                try {
                    calorieGoal = Integer.parseInt(scanner.nextLine());
                    if (calorieGoal >= 1 && calorieGoal <= 50000) {
                        AddCalorieGoalCommand addCalorieGoalCommand = new
                                AddCalorieGoalCommand(calorieGoal, date, time);
                        addCalorieGoalCommand.execute();
                        saveLogsToFile("data/data.txt");
                        k++;
                    } else {
                        System.out.println("Please input a whole number between 1 and 50000!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input a whole number between 1 and 50000!");
                }
            }

            // Prompt for setting daily water goal
            while (l < 1) {
                System.out.println("Please set your daily water goal (in ml): ");
                try {
                    waterGoal = Integer.parseInt(scanner.nextLine());
                    if (waterGoal >= 1 && waterGoal <= 6000) {
                        AddWaterGoalCommand addWaterGoalCommand = new
                                AddWaterGoalCommand(waterGoal, date, time);
                        addWaterGoalCommand.execute();
                        saveLogsToFile("data/data.txt");
                        l++;
                    } else {
                        System.out.println("Please input a whole number between 1 and 6000!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input a whole number between 1 and 6000!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("You can now start logging data! Type 'help' " +
                "if you are not sure how to use ActiveEdge.");
    }






    /**
     * Fetches and loads data from a specified data file into the application's memory.
     * This method attempts to read tasks from the file, parsing each line to recreate
     * the appropriate {@code Task} objects. The tasks are then added to the {@code TaskList}.
     */
    public static void fetchData() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        File file = new File(filePath);

        if (!file.exists()) {
            createFile(filePath);
        }
        if (file.length() == 0 ) {
            listEmpty();
        } else {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String log = scanner.nextLine();
                    String dateTimeStr = extractDateTimeString(log);
                    LocalDateTime dateTime = parseDateTime(dateTimeStr);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String date = dateTime.format(dateFormatter);
                    String time = dateTime.format(timeFormatter);
                    if (log.startsWith("Meal")) {
                        //replaces parentheses with a white space
                        log = log.replace("(", "").replace(")", "");
                        //splits string by "|" and white spaces
                        String[] items = log.split("\\s*\\|\\s*|\\s+");
                        int len = items.length;
                        assert len >= 10;

                        String mealName = "";
                        //len-7 is the last item[] of the mealname. if mealname is fried chicken
                        // then item[len-7] = chicken
                        for(int i = 1; i <= len-9; i++) {
                            if( i < len-9 ) {
                                mealName = mealName + items[i] + " ";
                            } else {
                                mealName = mealName + items[i];
                            }
                        }
                        int servings = Integer.parseInt(items[len-8]);
                        int mealCalories = Integer.parseInt(items[len-6]);
                        LogMeal newLog = new LogMeal(mealName, servings, mealCalories, date, time);
                        LogList.logList.add(newLog);
                    } else if (log.startsWith("Goal")) {
                        String[] items = log.trim().split(" ");
                        LogGoals newLog = new LogGoals(items[1], Integer.parseInt(items[2]), date, time);
                        LogList.logList.add(newLog);
                    } else if (log.startsWith("Water")) {
                        log = log.replace("(", "").replace(")", "");
                        String[] items = log.split("\\s*\\|\\s*|\\s+");
                        LogWater newLog = new LogWater(Integer.parseInt(items[1]), date, time);
                        LogList.logList.add(newLog);
                    } else if (log.startsWith("Height")) {
                        String[] items = log.trim().split(" ");
                        LogHeight newHeight = new LogHeight(Integer.parseInt(items[1]), date, time);
                        UserDetailsList.detailsList.add(newHeight);
                    } else if (log.startsWith("Weight")) {
                        String[] items = log.trim().split(" ");
                        LogWeight newWeight = new LogWeight(Integer.parseInt(items[1]), date, time);
                        UserDetailsList.detailsList.add(newWeight);
                    } else if (log.startsWith("BMI")) {
                        String[] items = log.trim().split(" ");
                        LogBMI newBMI = new LogBMI(Integer.parseInt(items[1]), date, time);
                        UserDetailsList.detailsList.add(newBMI);
                    } else if (log.startsWith("Exercise")){
                        log = log.replace("(", "").replace(")", "");
                        String[] items = log.split("\\s*\\|\\s*|\\s+");
                        int len = items.length;
                        assert len >= 10;
                        String exerciseName = "";
                        for(int i = 1; i <= len-9; i++) {
                            if( i < len-9 ) {
                                exerciseName = exerciseName + items[i] + " ";
                            } else {
                                exerciseName = exerciseName + items[i];
                            }
                        }
                        LogExercise newLog = new LogExercise(exerciseName,
                                Integer.parseInt(items[len - 8]),
                                Integer.parseInt(items[len - 6]), date, time);
                        LogList.logList.add(newLog);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static String extractDateTimeString(String log) {
        // Extracting date-time string between "Recorded on: " and ")"
        int startIndex = log.indexOf("Recorded on: ") + "Recorded on: ".length();
        int endIndex = log.lastIndexOf(")");
        return log.substring(startIndex, endIndex);
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            // Use the correct DateTimeFormatter for your date-time strings
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date-time: " + e.getMessage());
            // Handle the error, e.g., by returning a default date-time or logging the error
            return LocalDateTime.now(); // Default to the current date-time
        }
    }
}

