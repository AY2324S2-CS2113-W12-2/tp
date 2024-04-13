package command;

import activeedge.ui.CommandUi;

public class FindCommand extends Command {
    static final String LINE = "____________________________________________________________\n";
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }
    public void execute() {
        if(input.trim().length() > 4){
            String word = input.split(" ")[1];
            CommandUi.printMatchingLog(word);
        } else {
            System.out.println(LINE +"Oh no! You are missing the keyword you want to search for!\n" + LINE);
        }
    }
}
