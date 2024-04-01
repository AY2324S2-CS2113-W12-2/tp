package command;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        LogExerciseCommand logExerciseCommand = new LogExerciseCommand("running", 30, 30, dateTime, true);
        assertEquals("running", logExerciseCommand.exerciseName);
        assertEquals(30, logExerciseCommand.duration);
        assertEquals(30, logExerciseCommand.caloriesBurnt);
    }

}
