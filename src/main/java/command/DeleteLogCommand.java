package command;


import activeedge.log.Log;
import activeedge.log.LogList;
import activeedge.log.LogWater;

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
        // Search for the log with the specified description
        boolean logFound = false;
        int countIndex = 0;
        for (int i = 0; i < LogList.logList.size(); i++) {
            Log log = LogList.logList.get(i);
            if (log.getDescription().toLowerCase().startsWith("water")) {
                if (log instanceof LogWater) { // Check if it's a WaterLog before casting
                    LogWater logWater = (LogWater) log;

                    if (((logWater.getQuantity()) + " ml").equalsIgnoreCase(description)) {
                        countIndex = countIndex + 1;
                        if(countIndex == index){
                            Log deletedLog = LogList.delete(i);
                            CommandUi.printLogDeletedMessage(deletedLog);
                            logFound = true;
                            break;
                        }
                    }
                }
            } else if (log.getDescription().equalsIgnoreCase(description)) {
                countIndex = countIndex + 1;
                if (countIndex == index){
                    Log deletedLog = LogList.delete(i);
                    CommandUi.printLogDeletedMessage(deletedLog);
                    logFound = true;
                    break;
                }
            }
        }

        if(index > countIndex){
            CommandUi.printDeleteMealInvalidIndexMessage();
            this.errorRaised = true;
        }
        if (!logFound && !errorRaised) {
            CommandUi.printLogNotFoundMessage();
        }
    }
}
