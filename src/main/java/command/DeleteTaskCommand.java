package command;

import activeedge.task.LogWater;
import activeedge.ui.CommandUi;
import activeedge.task.Task;
import activeedge.task.TaskList;

public class DeleteTaskCommand extends Command{
    private static String description;
    private static int index;
    private static boolean errorRaised;

    public DeleteTaskCommand(String inputTrimmed) {
        String[] parts = inputTrimmed.split(" ", 2); // Split at the first space
        String[] indexParts;

        this.errorRaised = false;
        if(parts.length == 2){
            if(parts[1].contains("i/")){
                indexParts = parts[1].trim().split("i/");
                this.index = Integer.parseInt(indexParts[1].trim());
                this.description = indexParts[0].trim();
                if(index <= 0){
                    CommandUi.printInvalidItemIndexMessage();
                    this.errorRaised = true;
                }
            } else {
                //If no index to be deleted is passed, 1 is considered as the index.
                this.index = 1;
                this.description = parts[1].trim();
            }
        }else {
            CommandUi.printInvalidDeleteFormatMessage();
            this.errorRaised = true;
        }
    }

    public void execute() {
        // Search for the task with the specified description
        boolean taskFound = false;
        int countIndex = 0;
        for (int i = 0; i < TaskList.tasksList.size(); i++) {
            Task task = TaskList.tasksList.get(i);
            if (task.getDescription().toLowerCase().startsWith("water")) {
                if (task instanceof LogWater) { // Check if it's a WaterTask before casting
                    LogWater waterTask = (LogWater) task;

                    if (((waterTask.getQuantity()) + " ml").equalsIgnoreCase(description)) {
                        countIndex = countIndex + 1;
                        if(countIndex == index){
                            Task deletedTask = TaskList.delete(i);
                            CommandUi.printTaskDeletedMessage(deletedTask);
                            taskFound = true;
                            break;
                        }
                    }
                }
            } else if (task.getDescription().equalsIgnoreCase(description)) {
                countIndex = countIndex + 1;
                if (countIndex == index){
                    Task deletedTask = TaskList.delete(i);
                    CommandUi.printTaskDeletedMessage(deletedTask);
                    taskFound = true;
                    break;
                }
            }
        }

        if(index > countIndex){
            CommandUi.printDeleteMealInvalidIndexMessage();
            errorRaised = true;
        }
        if (!taskFound && !errorRaised) {
            CommandUi.printTaskNotFoundMessage();
        }
    }
}
