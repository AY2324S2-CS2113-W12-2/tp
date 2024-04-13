package command;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddNewExerciseTest {
    @Test
    public void testAddNewMeal() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        AddExerciseItemCommand addExerciseItemCommand = new AddExerciseItemCommand("Sky diving", 30, 15, date, time);
        assertEquals("Sky diving", addExerciseItemCommand.getExerciseName());
        assertEquals(30, addExerciseItemCommand.getDuration());
        assertEquals(15, addExerciseItemCommand.getCaloriesBurntPerMinute());
    }
}
