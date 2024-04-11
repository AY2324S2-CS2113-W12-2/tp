package command;

<<<<<<< HEAD
import activeedge.task.Task;
import activeedge.task.LogWater;
=======
import activeedge.log.Log;
import activeedge.log.LogWater;
>>>>>>> cb76f05 (change all task to logs, fixed summary function)
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        // Create a mock task list
        ArrayList<Log> logList = new ArrayList<>();
        logList.add(new LogWater(200, date, time)); // Adding water intake
        logList.add(new LogWater(300, date, time)); // Adding water intake

        ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();

        // Call method getTotalWaterIntake using reflection or make it package-private for testing
        int totalWaterIntake = viewWaterIntakeCommand.getTotalWaterIntake(logList);

        // Verify total water intake
        assertEquals(500, totalWaterIntake);
    }

}
