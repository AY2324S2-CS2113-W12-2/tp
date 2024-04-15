package command;

import activeedge.userdetails.LogWeight;
import static activeedge.userdetails.UserDetailsList.detailsList;

/**
 * This class represents a command to add a weight entry to the user's health record.
 * It logs the weight along with the corresponding date and time when the measurement was recorded.
 */
public class AddWeightCommand {
    protected Integer weight;
    protected String date;
    protected String time;

    /**
     * Constructs an AddWeightCommand with the specified weight, date, and time.
     *
     * @param weight The weight of the user in kilograms.
     * @param date The date on which the weight measurement is recorded.
     * @param time The time at which the weight measurement is recorded.
     */
    public AddWeightCommand(Integer weight, String date, String time) {
        this.weight = weight;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to log the user's weight.
     * This method creates a new log entry for the user's weight and adds it to the user details list.
     *
     * @throws ActiveEdgeException If there is an error during the execution of the command.
     */
    public void execute() throws ActiveEdgeException {
        LogWeight logWeight = new LogWeight(weight, date, time);
        detailsList.add(logWeight);
    }
}

