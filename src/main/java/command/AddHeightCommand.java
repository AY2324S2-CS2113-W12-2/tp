package command;

import activeedge.userdetails.LogHeight;
import static activeedge.userdetails.UserDetailsList.detailsList;

/**
 * This class represents a command to add a height entry to the user's health record.
 * It logs the height along with the corresponding date and time when the measurement was recorded.
 */
public class AddHeightCommand {
    protected Integer height;
    protected String date;
    protected String time;

    /**
     * Constructs an AddHeightCommand with the specified height, date, and time.
     *
     * @param height The height of the user in centimeters.
     * @param date The date on which the height measurement is recorded.
     * @param time The time at which the height measurement is recorded.
     */
    public AddHeightCommand (Integer height, String date, String time) {
        this.height = height;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to log the user's height.
     * This method creates a new log entry for the user's height and adds it to the user details list.
     *
     * @throws ActiveEdgeException If there is an error during the execution of the command.
     */
    public void execute() throws ActiveEdgeException {
        LogHeight logHeight = new LogHeight(height,date, time);
        detailsList.add(logHeight);
    }
}
