package command;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link AddExerciseItemCommand}. It uses JUnit to run tests that check the correct
 * creation and initialization of {@link AddExerciseItemCommand} instances.
 *
 * The tests ensure that when an {@link AddExerciseItemCommand} is created with specified parameters,
 * those parameters are accurately reflected in the object's state, ensuring the integrity of the
 * exercise logging process.
 */
public class AddNewExerciseTest {

    /**
     * Tests the functionality of adding a new exercise log with a given name, duration,
     * and calories burned per minute. It checks that the {@link AddExerciseItemCommand} correctly initializes and stores these values.
     *
     * The test uses the current date and time to simulate the logging as it would happen in a real scenario,
     * validating that the command behaves as expected under typical conditions.
     */
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
