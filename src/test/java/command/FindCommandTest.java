package command;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void testFindCommandWithKeyword() throws ActiveEdgeException {
        System.setOut(new PrintStream(outputStreamCaptor));

        // Keyword for testing
        String keyword = "example";

        // Constructing FindCommand with input containing keyword
        new FindCommand("find " + keyword);

        // Asserting that the expected output is printed
        String expectedOutput = "Matching tasks for 'example' are:\n" +
                "Task 1\n" +
                "Task 2\n"; // Assuming 'Task 1' and 'Task 2' are matching tasks
        assertEquals(expectedOutput, outputStreamCaptor.toString());

        // Resetting the standard output stream
        System.setOut(System.out);
    }

    @Test
    public void testFindCommandWithoutKeyword() throws ActiveEdgeException {
        System.setOut(new PrintStream(outputStreamCaptor));

        // Constructing FindCommand with input not containing a keyword
        new FindCommand("find");

        // Asserting that the expected output is printed
        String expectedOutput = "____________________________________________________________\n" +
                "Oh no! You are missing the keyword you want to search for!\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());

        // Resetting the standard output stream
        System.setOut(System.out);
    }
}


