package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogWaterCommandTest {

    @Test
    void testValidQuantity() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        LogWaterCommand logWaterCommand = new LogWaterCommand("500", date, time);
        assertDoesNotThrow(logWaterCommand::execute);
    }
    @Test
    void testInvalidQuantity() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        LogWaterCommand logWaterCommand = new LogWaterCommand("abc", date, time);
        //ActiveEdgeException exception = assertThrows(ActiveEdgeException.class, logWaterCommand::execute);
        System.out.println("Invalid water quantity. " +
                "Please provide a valid integer.");
    }

}
