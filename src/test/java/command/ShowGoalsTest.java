package command;

import activeedge.log.LogGoals;
import activeedge.log.LogList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowGoalsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testExecute_noGoalsSet() {
        // Clear the log list before the test
        LogList.clearLogs();

        // Create an instance of ShowGoalsCommand
        ShowGoalsCommand showGoalsCommand = new ShowGoalsCommand();

        // Execute the command
        showGoalsCommand.execute();

        // Assert that the output matches the expected message when no goals are set
        assertEquals("Current goals\nDaily calories intake goal: " + 0 + " cal" +
                        "\nDaily water intake goal: " + 0 + " ml",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testExecute_withGoalsSet() {
        // Clear the log list before setting goals
        LogList.clearLogs();

        // Create mock goal logs
        LogGoals LogCalorieGoal = new LogGoals("Calorie Goal", 2000,
                "2024-04-12", "08:00");
        LogGoals LogWaterGoal = new LogGoals("Water Goal", 2000,
                "2024-04-12", "08:00");

        // Add goals to the log list
        LogList.add(LogCalorieGoal);
        LogList.add(LogWaterGoal);

        // Create an instance of ShowGoalsCommand
        ShowGoalsCommand showGoalsCommand = new ShowGoalsCommand();

        // Execute the command
        showGoalsCommand.execute();

        // Assert that the output matches the expected message with the set goals
        assertEquals("Current goals \nDaily calories intake goal: " + 2000 + " cal" +
                        "\nDaily water intake goal: " + 2000 + " ml",
                outputStreamCaptor.toString().trim());
    }
}

