package activeedge;

import activeedge.log.LogGoals;
import activeedge.log.LogMeal;
import activeedge.log.LogExercise;
import activeedge.log.LogWater;
import activeedge.ui.ByeUi;
import activeedge.userdetails.LogBMI;
import activeedge.userdetails.LogHeight;
import activeedge.userdetails.LogWeight;
import activeedge.userdetails.UserDetailsList;

import command.AddBMICommand;
import command.AddHeightCommand;
import command.AddWeightCommand;
import command.AddCalorieGoalCommand;
import command.AddWaterGoalCommand;
import command.ActiveEdgeException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static activeedge.log.LogList.logList;
import static activeedge.userdetails.UserDetailsList.detailsList;

/**
 * The {@code Storage} class handles file operations for the Health Tracker application.
 * It includes methods for ensuring directory existence, creating files, saving logs to files,
 * and fetching data from files.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "data/data.txt";
    
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
     * Deletes all data from the data file.
     * It throws an ActiveEdgeException if there is any error during deletion.
     */
    public static void deleteData() throws ActiveEdgeException {
        try {
            // Delete data file
            File dataFile = new File(DATA_FILE_PATH);
            if (dataFile.exists()) {
                dataFile.delete();
            }
        } catch (Exception e) {
            // If any error occurs during deletion, throw an ActiveEdgeException
            throw new ActiveEdgeException("Error deleting data file: " + e.getMessage());
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
        ensureDirectoryExists(filePath);
        try (FileWriter fw = new FileWriter(filePath, false)) {
            for (int i = 0; i < UserDetailsList.detailsList.size(); i++) {
                String out = UserDetailsList.detailsList.get(i).toString();
                fw.write(out + "\n");
            }
            for (int i = 0; i < logList.size(); i++) {
                String out = logList.get(i).toString();
                fw.write(out + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving logs to file: " + e.getMessage());
        }
    }

    public static void listEmpty() throws ActiveEdgeException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);
        System.out.print("\n");
        System.out.println("Since you are new here or your details are missing,\n" +
                "let's start with a few questions to set things up!");


        int heightInput = userHeight(date, time);
        int weightInput = userWeight(date, time);
        userBMI(heightInput, weightInput, date, time);

        userCalorieGoal(date, time);
        userWaterGoal(date, time);

        System.out.println("You can now start logging data! Type 'help' " +
                "if you are not sure how to use ActiveEdge.");
    }

    public static int userHeight(String date, String time) throws ActiveEdgeException {
        int heightInput = 0;
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        while (counter < 1) {
            System.out.println("Please input your height (in cm): ");
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input.trim())) { // Check if the user wants to exit
                ByeUi.printByeMessage();
                System.exit(0);
            }
            try {
                heightInput = Integer.parseInt(input);
                if (heightInput >= 50 && heightInput <= 300) {
                    AddHeightCommand addHeightCommand = new
                            AddHeightCommand(heightInput, date, time);
                    addHeightCommand.execute();
                    saveLogsToFile("data/data.txt");
                    counter++;
                } else {
                    System.out.println("Please input a whole number between 50 and 300!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a whole number between 50 and 300!");
            }
        }
        return heightInput;
    }

    public static int userWeight(String date, String time) throws ActiveEdgeException {
        int counter = 0;
        int weightInput = 0;
        Scanner scanner = new Scanner(System.in);
        while (counter < 1) {
            System.out.println("Please input your weight (in kg): ");
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input.trim())) { // Check if the user wants to exit
                ByeUi.printByeMessage();
                System.exit(0);
            }
            try {
                weightInput = Integer.parseInt(input);
                if (weightInput >= 1 && weightInput <= 700) {
                    AddWeightCommand addWeightCommand = new
                            AddWeightCommand(weightInput, date, time);
                    addWeightCommand.execute();
                    saveLogsToFile("data/data.txt");
                    counter++;
                } else {
                    System.out.println("Please input a whole number between 1 and 700!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a whole number between 1 and 700!");
            }
        }
        return weightInput;
    }

    public static void userBMI(int heightInput, int weightInput, String date, String time)
            throws ActiveEdgeException {
        AddBMICommand addBMICommand = new AddBMICommand(heightInput, weightInput, date, time);
        addBMICommand.execute();
        saveLogsToFile("data/data.txt");
    }
    public static void userCalorieGoal(String date, String time) {
        int counter = 0;
        int calorieGoal = 0;
        Scanner scanner = new Scanner(System.in);
        while (counter < 1) {
            System.out.println("Please set your daily calorie goal (in kcal): ");
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input.trim())) { // Check if the user wants to exit
                ByeUi.printByeMessage();
                System.exit(0);
            }
            try {
                calorieGoal = Integer.parseInt(input);
                if (calorieGoal >= 1 && calorieGoal <= 50000) {
                    AddCalorieGoalCommand addCalorieGoalCommand = new
                            AddCalorieGoalCommand(calorieGoal, date, time);
                    addCalorieGoalCommand.execute();
                    saveLogsToFile("data/data.txt");
                    counter++;
                } else {
                    System.out.println("Please input a whole number between 1 and 50000!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a whole number between 1 and 50000!");
            }
        }
    }

    public static void userWaterGoal(String date, String time) {
        int counter = 0;
        int waterGoal = 0;
        Scanner scanner = new Scanner(System.in);
        while (counter < 1) {
            System.out.println("Please set your daily water goal (in ml): ");
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input.trim())) { // Check if the user wants to exit
                ByeUi.printByeMessage();
                System.exit(0);
            }
            try {
                waterGoal = Integer.parseInt(input);
                if (waterGoal >= 1 && waterGoal <= 6000) {
                    AddWaterGoalCommand addWaterGoalCommand = new
                            AddWaterGoalCommand(waterGoal, date, time);
                    addWaterGoalCommand.execute();
                    saveLogsToFile("data/data.txt");
                    counter++;
                } else {
                    System.out.println("Please input a whole number between 1 and 6000!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a whole number between 1 and 6000!");
            }
        }
    }

    public static void listMissingData() throws Exception {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        System.out.println("Some of your details are missing! Please input them below to continue!");

        int numberOfLines = lineCounter();
        if (numberOfLines < 2 && numberOfLines >= 1) {
            int userHeight = 0;
            String[] item = detailsList.get(0).toString().split(" ");
            userHeight = Integer.parseInt(item[1]);
            int userWeight = userWeight(date, time);
            userBMI(userHeight, userWeight, date, time);
            userCalorieGoal(date, time);
            userWaterGoal(date, time);
        } else if (numberOfLines < 3 && numberOfLines >= 2) {
            userCalorieGoal(date, time);
            userWaterGoal(date, time);
        } else if (numberOfLines < 5 && numberOfLines >= 4) {
            userWaterGoal(date, time);
        }
        System.out.println("You can now start logging data! Type 'help' " +
                "if you are not sure how to use ActiveEdge.");
    }

    public static int lineCounter() throws Exception {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        List<String> lines = Files.readAllLines(Path.of(filePath));
        return lines.size();
    }
    /**
     * Fetches and loads data from a specified data file into the application's memory.
     * This method attempts to read tasks from the file, parsing each line to recreate
     * the appropriate {@code Task} objects. The tasks are then added to the {@code TaskList}.
     */
    public static void fetchData() throws ActiveEdgeException {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        File file = new File(filePath);
        ensureDirectoryExists(filePath);
        if (!file.exists()) {
            createFile(filePath);
        }
        if (file.length() == 0) {
            listEmpty();
        } else {
            try (Scanner scanner = new Scanner(file)) {
                int numberOfLines = lineCounter();
                if (numberOfLines < 5 && numberOfLines >= 1) {
                    while (scanner.hasNext()) {
                        String log = scanner.nextLine();
                        String dateTimeStr = extractDateTimeString(log);
                        LocalDateTime dateTime = parseDateTime(dateTimeStr);
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                        String initialDate = dateTime.format(dateFormatter);
                        String initialTime = dateTime.format(timeFormatter);
                        if (log.startsWith("Height")) {
                            String[] items = log.trim().split(" ");
                            LogHeight newHeight = new LogHeight(Integer.parseInt(items[1]),
                                    initialDate, initialTime);
                            UserDetailsList.detailsList.add(newHeight);
                        } else if (log.startsWith("Weight")) {
                            String[] items = log.trim().split(" ");
                            LogWeight newWeight = new LogWeight(Integer.parseInt(items[1]),
                                    initialDate, initialTime);
                            UserDetailsList.detailsList.add(newWeight);
                        } else if (log.startsWith("BMI")) {
                            String[] items = log.trim().split(" ");
                            LogBMI newBMI = new LogBMI(Integer.parseInt(items[1]),
                                    initialDate, initialTime);
                            UserDetailsList.detailsList.add(newBMI);
                        } else if (log.startsWith("Goal")) {
                            String[] items = log.trim().split(" ");
                            LogGoals newLog = new LogGoals(items[1], Integer.parseInt(items[2]),
                                    initialDate, initialTime);
                            logList.add(newLog);
                        }
                    }
                    listMissingData();
                } else {
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
                            for (int i = 1; i <= len - 9; i++) {
                                if (i < len - 9) {
                                    mealName = mealName + items[i] + " ";
                                } else {
                                    mealName = mealName + items[i];
                                }
                            }
                            int servings = Integer.parseInt(items[len - 8]);
                            int mealCalories = Integer.parseInt(items[len - 6]);
                            LogMeal newLog = new LogMeal(mealName, servings, mealCalories, date, time);
                            logList.add(newLog);
                        } else if (log.startsWith("Goal")) {
                            String[] items = log.trim().split(" ");
                            LogGoals newLog = new LogGoals(items[1], Integer.parseInt(items[2]), date, time);
                            logList.add(newLog);
                        } else if (log.startsWith("Water")) {
                            log = log.replace("(", "").replace(")", "");
                            String[] items = log.split("\\s*\\|\\s*|\\s+");
                            LogWater newLog = new LogWater(Integer.parseInt(items[1]), date, time);
                            logList.add(newLog);
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
                        } else if (log.startsWith("Exercise")) {
                            log = log.replace("(", "").replace(")", "");
                            String[] items = log.split("\\s*\\|\\s*|\\s+");
                            int len = items.length;
                            assert len >= 10;
                            String exerciseName = "";
                            for (int i = 1; i <= len - 9; i++) {
                                if (i < len - 9) {
                                    exerciseName = exerciseName + items[i] + " ";
                                } else {
                                    exerciseName = exerciseName + items[i];
                                }
                            }
                            LogExercise newLog = new LogExercise(exerciseName,
                                    Integer.parseInt(items[len - 8]),
                                    Integer.parseInt(items[len - 6]), date, time);
                            logList.add(newLog);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
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

