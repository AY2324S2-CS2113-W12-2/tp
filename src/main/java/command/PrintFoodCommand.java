package command;

import activeedge.FoodData;

public class PrintFoodCommand extends Command {
    @Override
    public void execute() {
        FoodData.printFood();
    }
}