package command;

/**
 * Represents an invalid command. This command is used to handle situations
 * where the input command is not recognized or cannot be executed.
 * When executed, this command prints the specified error message to the console.
 */
public class InvalidCommand extends Command {
    private String errorMessage;

    /**
     * Constructs an InvalidCommand object with the specified error message.
     *
     * @param errorMessage the error message to be printed when the command is executed
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the invalid command by printing the error message to the console.
     */
    @Override
    public void execute() {
        System.out.println(errorMessage);
    }
}
