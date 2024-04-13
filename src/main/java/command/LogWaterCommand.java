package command;

import activeedge.log.LogList;
import activeedge.log.LogWater;
import activeedge.ui.CommandUi;

/**
 * Represents a command to log water intake.
 */
public class LogWaterCommand extends Command{
    private String quantityString;
    private String date;
    private String time;

    /**
     * Constructs a LogWaterCommand object with the specified quantity string.
     *
     * @param quantityString The string representing the quantity of water to log.
     */
    public LogWaterCommand(String quantityString, String date, String time) {
        this.quantityString = quantityString;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to log water intake.
     */
    public void execute() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityString);
            //@@author nikhil-2101
            //assert quantity >= 0;
            if (quantity <= 0) {
                System.out.println("Water quantity must be above 0. Please try again.");
            } else {
                LogWater logWater = new LogWater(quantity, date, time);
                LogList.logList.add(logWater);
                CommandUi.printWaterLogMessage(logWater);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid water quantity. Please provide a valid integer.");
        }
    }
}
