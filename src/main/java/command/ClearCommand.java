/**
 * The ClearCommand class represents a command to clear all tasks and user details in the system.
 * It provides functionality to execute the command.
 */
package command;

import activeedge.Storage;
import activeedge.log.LogList;
import activeedge.ui.CommandUi;
import activeedge.userdetails.UserDetailsList;

/**
 * The ClearCommand class represents a command to clear all tasks and user details in the system.
 * It provides functionality to execute the command.
 */
public class ClearCommand extends Command{

    /**
     * Constructs a ClearCommand object.
     * This constructor requires no input.
     */
    public ClearCommand() {
        // No input needed for this command
    }

    /**
     * Executes the clear command.
     * Checks if both task list and user details list are empty.
     * If both lists are already empty, it prints a message indicating the data is already cleared.
     * Otherwise, it deletes both lists and deletes the data from the file system.
     */
    public void execute() {
        if (LogList.logList.isEmpty() && UserDetailsList.detailsList.isEmpty()) {
            CommandUi.printDataAlreadyClearedMessage();
        } else {
            CommandUi.printAllLogsClearedMessage();
            try {
                Storage.deleteData(); // Delete data from the file system
                Storage.listEmpty();
            } catch (ActiveEdgeException e) {
                throw new RuntimeException(e);
            }
        }
    }

}