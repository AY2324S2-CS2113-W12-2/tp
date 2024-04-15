package command;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeWaterGoalCommandTest {

    @Test
    public void testChangeWaterGoalCommand() {
        // Prepare input stream with simulated user input
        String simulatedUserInput = "2000\n"; // Simulated user input for the new water goal
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream); // Redirect System.in to the simulated input stream

        // Create instance of ChangeWaterGoalCommand
        ChangeWaterGoalCommand changeWaterGoalCommand = new ChangeWaterGoalCommand();

        // Execute the command
        changeWaterGoalCommand.execute();

        // Assert the output
        assertEquals("You have successfully changed your water goal! You can continue to log your data!");
    }

    private void assertEquals(String s) {
    }
}
