package command;

<<<<<<< HEAD:src/main/java/command/DeleteTaskCommand.java
import activeedge.task.LogWater;
=======
import activeedge.log.Log;
import activeedge.log.LogList;
import activeedge.log.LogWater;
>>>>>>> cb76f05 (change all task to logs, fixed summary function):src/main/java/command/DeleteLogCommand.java
import activeedge.ui.CommandUi;

public class DeleteLogCommand {
    private String description;
    private int index;
    private boolean errorRaised;

    public DeleteLogCommand(String inputTrimmed) {
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
<<<<<<< HEAD:src/main/java/command/DeleteTaskCommand.java
        for (int i = 0; i < TaskList.tasksList.size(); i++) {
            Task task = TaskList.tasksList.get(i);
            if (task.getDescription().toLowerCase().startsWith("water")) {
                if (task instanceof LogWater) { // Check if it's a WaterTask before casting
                    LogWater waterTask = (LogWater) task;
=======
        for (int i = 0; i < LogList.logList.size(); i++) {
            Log log = LogList.logList.get(i);
            if (log.getDescription().toLowerCase().startsWith("water")) {
                if (log instanceof LogWater) { // Check if it's a WaterTask before casting
                    LogWater logWater = (LogWater) log;
>>>>>>> cb76f05 (change all task to logs, fixed summary function):src/main/java/command/DeleteLogCommand.java

                    if (((logWater.getQuantity()) + " ml").equalsIgnoreCase(description)) {
                        countIndex = countIndex + 1;
                        if(countIndex == index){
                            Log deletedLog = LogList.delete(i);
                            CommandUi.printTaskDeletedMessage(deletedLog);
                            taskFound = true;
                            break;
                        }
                    }
                }
            } else if (log.getDescription().equalsIgnoreCase(description)) {
                countIndex = countIndex + 1;
                if (countIndex == index){
                    Log deletedLog = LogList.delete(i);
                    CommandUi.printTaskDeletedMessage(deletedLog);
                    taskFound = true;
                    break;
                }
            }
        }

        if(index > countIndex){
            CommandUi.printDeleteMealInvalidIndexMessage();
            this.errorRaised = true;
        }
        if (!taskFound && !errorRaised) {
            CommandUi.printTaskNotFoundMessage();
        }
    }
}
