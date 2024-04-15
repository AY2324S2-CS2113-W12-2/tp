package command;

import activeedge.FoodData;

/**
 * Represents a command to print food data.
 * When executed, this command prints food data using the FoodData class.
 */
public class PrintFoodCommand extends Command {

    /**
     * Executes the command by printing food data using FoodData.
     */
    @Override
    public void execute() {
        FoodData.printFood();
    }
}
