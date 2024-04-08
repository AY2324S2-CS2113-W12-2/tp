package command;

import activeedge.task.Task;
import activeedge.task.WaterTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;


public class ViewWaterIntakeCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testGetTotalWaterIntake() {
        LocalDateTime dateTime = LocalDateTime.now();
        // Create a mock task list
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(new WaterTask(200, dateTime)); // Adding water intake
        tasksList.add(new WaterTask(300, dateTime)); // Adding water intake

        ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();

        // Call method getTotalWaterIntake using reflection or make it package-private for testing
        int totalWaterIntake = viewWaterIntakeCommand.getTotalWaterIntake(tasksList);

        // Verify total water intake
        assertEquals(500, totalWaterIntake);
    }

}
