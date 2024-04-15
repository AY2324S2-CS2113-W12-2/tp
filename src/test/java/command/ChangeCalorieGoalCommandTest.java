package command;

import activeedge.log.Log;
import activeedge.log.LogList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeCalorieGoalCommandTest {

    private MockScanner mockScanner;

    @BeforeEach
    public void setUp() {
        // Create a mock scanner for test input
        mockScanner = new MockScanner("2000"); // Input 2000 as the new calorie goal
        // Set the System.in to the mock scanner
        System.setIn(mockScanner);
    }

    @AfterEach
    public void tearDown() {
        // Reset System.in after each test
        System.setIn(System.in);
    }

    @Test
    public void testExecute() {
        // Create an instance of the ChangeCalorieGoalCommand
        ChangeCalorieGoalCommand command = new ChangeCalorieGoalCommand();
        // Execute the command
        command.execute();

        // Check if the log list contains the updated calorie goal
        boolean goalFound = false;
        for (Log log : LogList.logList) {
            if (log.toString().contains("Goal Calorie") && log.toString().contains("2000")) {
                goalFound = true;
                break;
            }
        }

        // Assert that the goal was found in the log list
        assertTrue(goalFound, "Expected log entry for goal calorie was not found");
    }

    // Mock Scanner class for testing input
    private static class MockScanner extends java.io.InputStream {
        private String input;

        public MockScanner(String input) {
            this.input = input;
        }

        @Override
        public int read() {
            if (input.length() > 0) {
                char nextChar = input.charAt(0);
                input = input.substring(1);
                return (int) nextChar;
            } else {
                return -1;
            }
        }
    }
}
