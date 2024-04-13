package command;
import activeedge.ExerciseData;

public class PrintExercisesCommand extends Command {
    @Override
    public void execute() {
        ExerciseData.printExercises();
    }
}
