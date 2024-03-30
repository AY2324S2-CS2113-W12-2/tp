package command;

import java.time.LocalDateTime;


public class AddExerciseItemCommand {
    protected String description;
    protected int duration;
    protected int caloriesBurntPerMinute;
    protected LocalDateTime dateTime;

    public AddExerciseItemCommand(String description, int duration, int caloriesPerSaving, LocalDateTime dateTime) {
        this.description = description;
        this.duration = duration;
        this.caloriesBurntPerMinute = caloriesBurntPerMinute;
        this.dateTime = dateTime;
    }

    public void execute() throws ActiveEdgeException {
        String[] newItem = {description, Integer.toString(caloriesBurntPerMinute)};
    }
}
