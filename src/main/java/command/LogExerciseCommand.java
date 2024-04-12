package command;

import activeedge.ui.CommandUi;
import activeedge.log.LogExercise;

import static activeedge.log.LogList.logList;

/**
 * Represents a command to log exercise activities into the system.
 * It stores information about an exercise, including its name, duration, and
 * the total amount of calories burnt by doing the exercise.
 */
public class LogExerciseCommand extends Command{
    protected String exerciseName; // The name of the exercise
    protected int duration; // The duration of the exercise in minutes
    protected int caloriesBurnt; // The total number of calories burnt during the exercise
    protected String date; // The date when the exercise was performed
    protected String time; // The time when the exercise was performed
    protected boolean isItemPresentInExerciseData; // Indicates if the exercise is present in the system's data

    /**
     * Constructs a LogExerciseCommand with the specified exercise details.
     * @param exerciseName The name of the exercise.
     * @param duration The duration of the exercise in minutes.
     * @param caloriesBurnt The total number of calories burnt during the exercise.
     * @param date The date when the exercise was performed.
     * @param time The time when the exercise was performed.
     * @param isItemPresentInExerciseData Indicates if the exercise is present in the system's data.
     */
    public LogExerciseCommand(String exerciseName, int duration, int caloriesBurnt, String date, String time,
                              boolean isItemPresentInExerciseData) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.date = date;
        this.time = time;
        this.isItemPresentInExerciseData = isItemPresentInExerciseData;
    }

    /**
     * Executes the exercise logging command.
     * It creates a new ExerciseTask with the exercise details and adds it to
     * the system's task list. After logging the exercise, it displays a confirmation message.
     * @throws ActiveEdgeException if any error occurs during the execution process.
     */
    public void execute() {

        if (isItemPresentInExerciseData){
            LogExercise logExercise = new LogExercise(exerciseName, duration, caloriesBurnt, date, time);
            logList.add(logExercise);
            CommandUi.printExerciseLogMessage(logExercise);
        } else {
            CommandUi.printExerciseItemNotFoundMessage(exerciseName);
        }
    }
}

