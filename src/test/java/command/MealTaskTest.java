package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author alvinnnnnnnnnn
public class MealTaskTest {
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        LogMealCommand logMealCommand = new LogMealCommand("Sushi", 3, 1050, date, time, true);
        assertEquals("Sushi", logMealCommand.getDescription());
        assertEquals(3, logMealCommand.getServings());
        assertEquals(1050, logMealCommand.getMealCalories());
    }
}
