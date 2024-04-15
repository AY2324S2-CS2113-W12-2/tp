package command;

import activeedge.ui.CommandUi;

public class FindCommand extends Command {
    static final String LINE = "____________________________________________________________\n";
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }
    public void execute() {
        // Trim the input to remove leading and trailing spaces
        String trimmedInput = input.trim();
        if (trimmedInput.length() > 4 && trimmedInput.startsWith("find")) {
            // Split the trimmed input to extract the keyword
            String[] parts = trimmedInput.split("\\s+", 3); // Split by any whitespace, limit to 3 parts
            if (parts.length > 1) {
                String word = parts[1];
                CommandUi.printMatchingLog(word);
            } else {
                System.out.println(LINE + "Oh no! You are missing the keyword you want to search for!\n" + LINE);
            }
        } else {
            System.out.println(LINE + "Invalid command format. Please provide a keyword to search for.\n" + LINE);
        }
    }
}
