package command;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the functionality of the LogExerciseCommand class.
 */
public class LogExerciseTest {

    /**
     *  The test case focuses on ensuring that the exercise name, duration, and calories burnt are set as expected.
     */
    @Test
    void testInitialization() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        LogExerciseCommand logExerciseCommand = new LogExerciseCommand("running", 30, 30, date, time, true);
        assertEquals("running", logExerciseCommand.exerciseName);
        assertEquals(30, logExerciseCommand.duration);
        assertEquals(30, logExerciseCommand.caloriesBurnt);
    }

}
