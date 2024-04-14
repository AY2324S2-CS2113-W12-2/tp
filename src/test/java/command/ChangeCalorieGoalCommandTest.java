package command;

import activeedge.log.Log;
import activeedge.log.LogList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeCalorieGoalCommandTest {

    @Test
    public void testExecute() {
        // Create a mock scanner for test input
        MockScanner mockScanner = new MockScanner("2000"); // Input 2000 as the new calorie goal

        // Set the System.in to the mock scanner
        System.setIn(mockScanner);

        // Create an instance of the ChangeCalorieGoalCommand
        ChangeCalorieGoalCommand command = new ChangeCalorieGoalCommand();

        // Execute the command
        command.execute();

        // Reset System.in
        System.setIn(System.in);

        // Check if the log list contains the updated calorie goal
        boolean goalFound = false;
        for (Log log : LogList.logList) {
            if (log.toString().startsWith("Goal Calorie")) {
                goalFound = true;
                assertEquals("Goal Calorie: 2000", log.toString());
                break;
            }
        }

        assertTrue(goalFound);
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
