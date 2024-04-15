package command;
import activeedge.ExerciseData;

/**
 * Represents a command to print exercises.
 * When executed, this command prints exercises using the ExerciseData class.
 */
public class PrintExercisesCommand extends Command {

    /**
     * Executes the command by printing exercises using ExerciseData.
     */
    @Override
    public void execute() {
        ExerciseData.printExercises();
    }
}
