package command;

import activeedge.ui.CommandUi;
import activeedge.ExerciseData;
import java.time.LocalDateTime;

public class AddExerciseItemCommand {
    protected String exerciseName;
    protected int duration;
    protected int caloriesBurntPerMinute;
    protected LocalDateTime dateTime;

    public AddExerciseItemCommand(String exerciseName, int duration, int caloriesBurntPerMinute,
                                  LocalDateTime dateTime) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurntPerMinute = caloriesBurntPerMinute;
        this.dateTime = dateTime;
    }

    public void execute() throws ActiveEdgeException {
        String[] newExercise = {exerciseName, Integer.toString(caloriesBurntPerMinute)};
        ExerciseData.exercisesList = appendItem(ExerciseData.exercisesList, newExercise);
        CommandUi.printAddExerciseMessage(exerciseName);
        LogExerciseCommand logExerciseCommand = new LogExerciseCommand(exerciseName, duration,
                caloriesBurntPerMinute*duration, dateTime, true);
        logExerciseCommand.execute();
    }

    private String[][] appendItem(String[][] originalArray, String[] newItem) {
        // Create a new array with one more row than the original
        String[][] newArray = new String[originalArray.length + 1][2]; // Assuming each item has 2 elements

        // Copy the contents of the original array to the new array
        for (int i = 0; i < originalArray.length; i++) {
            newArray[i] = originalArray[i];
        }

        // Add the new item to the last position of the new array
        newArray[newArray.length - 1] = newItem;

        return newArray;
    }
}

