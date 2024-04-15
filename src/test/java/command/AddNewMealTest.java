package command;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link AddFoodItemCommand} using JUnit. This class contains tests to verify that the
 * {@link AddFoodItemCommand} class correctly initializes and handles new food item entries
 * with specified attributes.
 */
public class AddNewMealTest {

    /**
     * Tests the initialization and properties setting of the {@link AddFoodItemCommand}.
     */
    @Test
    public void testAddNewMeal() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        AddFoodItemCommand addFoodItemCommand = new AddFoodItemCommand("Banana Cake", 3, 450, date, time);
        assertEquals("Banana Cake", addFoodItemCommand.getDescription());
        assertEquals(3, addFoodItemCommand.getServings());
        assertEquals(450, addFoodItemCommand.getCaloriesPerSaving());
    }
}
