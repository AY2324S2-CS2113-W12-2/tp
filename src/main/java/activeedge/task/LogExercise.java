package activeedge.task;

/**
 * Represents an exercise task that extends the functionality of a basic task
 * to include exercise-specific details. It captures the name, duration, and
 * calories burnt of an exercise activity.
 */
public class LogExercise extends Task{
    protected String exerciseName;
    protected int duration;
    protected Integer caloriesBurnt;
    protected String date;
    protected String time;


    /**
     * Constructs a new exercise log with the specified exercise name, duration, and calories burnt.
     *
     * @param exerciseName the name of the exercise
     * @param duration the duration of the exercise, in minutes
     * @param caloriesBurnt the number of calories burnt during the exercise
     */
    public LogExercise(String exerciseName, int duration, int caloriesBurnt, String date , String time){
        super(exerciseName);
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.date = date;
        this.time = time;
    }
    public String getExerciseName() {
        return description;
    }


    public int getDuration(){
        return duration;
    }

    public int getCaloriesBurnt(){
        return caloriesBurnt;
    }

    /**
     * Returns a string representation of the exercise task, including its name,
     * duration, and calories burnt.
     */
    @Override
    public String toString() {
        return "Exercise" + " | " + this.getExerciseName() + " | " + this.getDuration() + " mins | " +
                this.getCaloriesBurnt() + " cal (Recorded on: " + date + " " + time + ")";
    }
}
