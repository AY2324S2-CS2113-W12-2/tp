package command;

import activeedge.log.Log;
import activeedge.log.LogList;
import activeedge.log.LogWater;

import activeedge.ui.CommandUi;

/**
 * Represents a command to delete a log entry.
 */
public class DeleteLogCommand extends Command{
    private String description;
    private int index;
    private boolean errorRaised;

    /**
     * Constructs a DeleteLogCommand object with the specified input.
     *
     * @param inputTrimmed The trimmed input string containing the description and index of the log to delete.
     */
    public DeleteLogCommand(String inputTrimmed) {
        String[] parts = inputTrimmed.trim().split("\\s+", 2); // Split at the first space
        String[] indexParts;

        this.errorRaised = false;
        if(parts.length == 2){
            if(parts[1].contains("i/")){
                indexParts = parts[1].trim().split("\\s*i/\\s*");;
                this.index = Integer.parseInt(indexParts[1].trim());
                this.description = indexParts[0].trim().replaceAll("\\s+", " ");
                if(index <= 0){
                    CommandUi.printInvalidItemIndexMessage();
                    this.errorRaised = true;
                }
            } else {
                //If no index to be deleted is passed, 1 is considered as the index.
                this.index = 1;
                String[] descriptionParts = parts[1].split("\\s*i/\\s*|\\s+");
                this.description = descriptionParts[0].trim();
                if (descriptionParts.length > 1) {
                    String[] quantityParts = descriptionParts[1].trim().split("\\s+");
                    if (quantityParts.length > 0) {
                        try {
                            Integer.parseInt(quantityParts[0]);
                            String quantityWithoutSpaces = quantityParts[0].trim().replaceAll("\\s+", "");
                            this.description += " " + quantityWithoutSpaces;
                        } catch (NumberFormatException e) {
                            // If it's not a number, consider it as part of description
                            this.description += " " + descriptionParts[1].trim();
                        }
                    }
                }
            }
        } else {
            CommandUi.printInvalidDeleteFormatMessage();
            this.errorRaised = true;
        }
    }

    /**
     * Executes the command to delete a log entry.
     */
    public void execute() {
        // Search for the log with the specified description
        boolean logFound = false;
        int countIndex = 0;
        for (int i = 0; i < LogList.logList.size(); i++) {
            Log log = LogList.logList.get(i);
            String logDescription = log.getDescription().toLowerCase().trim();
            if (logDescription.startsWith("water")){
                if (log instanceof LogWater) {
                    String targetDescription = description.trim().replaceAll("\\s+", "");
                    LogWater logWater = (LogWater) log;
                    String quantityString = (logWater.getQuantity()) + "ml";
                    if (quantityString.equals(targetDescription)) {
                        countIndex++;
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
            errorRaised = true;
        }
        if (!logFound && !errorRaised) {
            CommandUi.printLogNotFoundMessage();
        }
    }
}
