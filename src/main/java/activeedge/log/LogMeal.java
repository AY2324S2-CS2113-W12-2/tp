package activeedge.log;

import java.util.Optional;

public class LogMeal extends Log {
    protected Integer servings;
    protected Integer mealCalories;
    protected String date;
    protected String time;

    public LogMeal (String meal, int servings, int mealCalories, String date, String time){
        super(meal);
        this.servings = Optional.ofNullable(servings).orElse(0);
        this.mealCalories = Optional.ofNullable(mealCalories).orElse(0);
        this.date = date;
        this.time = time;
    }

    public String getFoodName() {
        return description;
    }

    public int getServings(){
        return servings;
    }

    public int getMealCalories() {
        return mealCalories;
    }

    @Override
    public String toString() {
        return "Meal" + " | " + this.getDescription() + " | " + this.getServings() + " serving(s) | " +
                this.getMealCalories() + " kcal (Recorded on: " + date + " " + time + ")";
    }
}
