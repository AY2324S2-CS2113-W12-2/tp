package command;

import activeedge.ui.CommandUi;

public class ShowCaloriesCommand extends Command {

    public void execute() {
        CommandUi.printShowCalMessage();
    }
}
