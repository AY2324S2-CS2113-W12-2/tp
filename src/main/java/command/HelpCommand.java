package command;
import activeedge.ui.HelpUi;

/**
 * The HelpCommand class is responsible for handling the "help" command within the application.
 */
public class HelpCommand extends Command{

    @Override
    public void execute() {
        HelpUi.printHelpMessage();
    }
}
