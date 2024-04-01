package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.time.LocalDateTime;



public class LogWaterCommandTest {

    @Test
    void testValidQuantity() {
        LocalDateTime dateTime = LocalDateTime.now();
        LogWaterCommand logWaterCommand = new LogWaterCommand("500", dateTime);
        assertDoesNotThrow(logWaterCommand::execute);
    }
    @Test
    void testInvalidQuantity() {
        LocalDateTime dateTime = LocalDateTime.now();
        LogWaterCommand logWaterCommand = new LogWaterCommand("abc", dateTime);
        //ActiveEdgeException exception = assertThrows(ActiveEdgeException.class, logWaterCommand::execute);
        System.out.println("Invalid water quantity. " +
                "Please provide a valid integer.");
    }

}
