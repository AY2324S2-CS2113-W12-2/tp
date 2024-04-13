package command;
import activeedge.ui.HelpUi;

public class HelpCommand extends Command{

    @Override
    public void execute() {
        HelpUi.printHelpMessage();
    }
}
