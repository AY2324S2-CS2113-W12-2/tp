package command;

<<<<<<< HEAD
import activeedge.task.LogWater;
=======
import activeedge.log.LogList;
import activeedge.log.LogWater;
>>>>>>> cb76f05 (change all task to logs, fixed summary function)
import activeedge.ui.CommandUi;

import static activeedge.log.LogList.logList;

/**
 * Represents a command to log water intake.
 */
public class LogWaterCommand {
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
            assert quantity >= 0;
            if (quantity <= 0) {
                System.out.println("Water quantity must be above 0. Please try again.");
            } else {
<<<<<<< HEAD
                LogWater waterTask = new LogWater(quantity, date, time);
                TaskList.tasksList.add(waterTask);
                CommandUi.printWaterLogMessage(waterTask);
=======
                LogWater logWater = new LogWater(quantity, date, time);
                LogList.logList.add(logWater);
                CommandUi.printWaterLogMessage(logWater);
>>>>>>> cb76f05 (change all task to logs, fixed summary function)
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid water quantity. Please provide a valid integer.");
        }
    }
}
