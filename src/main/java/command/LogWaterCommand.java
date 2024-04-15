package command;

import activeedge.log.Log;
import activeedge.log.LogGoals;
import activeedge.log.LogList;
import activeedge.log.LogWater;
import activeedge.ui.CommandUi;

import static activeedge.log.LogList.logList;

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
            assert quantity >= 0;
            if (quantity <= 0) {
                System.out.println("Water quantity must be above 0. Please try again.");
            } else {
                LogWater logWater = new LogWater(quantity, date, time);
                LogList.logList.add(logWater);
                CommandUi.printWaterLogMessage(logWater);
                int totalWaterIntake = calculateTotalWaterIntake() + quantity;
                int dailyWaterIntakeGoal = getWaterGoal();

                if (totalWaterIntake > dailyWaterIntakeGoal) {
                    CommandUi.printWaterIntakeExceedingWarning();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid water quantity. Please provide a valid integer.");
        }
    }

    private int getWaterGoal() {
        for (Log log : LogList.logList) {
            if (log instanceof LogGoals && log.getDescription().startsWith("Water")) {
                return ((LogGoals) log).getGoalAmount();
            }
        }
        return 0;
    }

    private int calculateTotalWaterIntake() {
        int totalWaterIntake = 0;
        for (Log log : logList) {
            if (log instanceof LogWater) {
                totalWaterIntake += ((LogWater) log).getQuantity();
            }
        }
        return totalWaterIntake;
    }
}
