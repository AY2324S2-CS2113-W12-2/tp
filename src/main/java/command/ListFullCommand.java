package command;

import activeedge.ui.CommandUi;
public class ListFullCommand extends Command {

    public void execute(){
        CommandUi.printFullList();
    }
}
