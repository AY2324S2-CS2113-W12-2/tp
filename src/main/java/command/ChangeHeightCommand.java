package command;

import activeedge.userdetails.UserDetails;
import activeedge.userdetails.UserDetailsList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static activeedge.userdetails.UserDetailsList.detailsList;

/**
 * This command class handles the process of changing a user's height within the user details system.
 * It updates the user's height and recalculates their BMI based on the new height.
 */
public class ChangeHeightCommand extends Command{

    /**
     * Executes the height change command. It first removes any existing height and BMI records,
     * then prompts the user to enter a new height, validates this input, and logs the new height and updated BMI.
     */
    public void execute() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        // Delete old height and BMI details from the user's details list
        for (int i = 0; i < detailsList.size(); i++) {
            UserDetails userDetails = UserDetailsList.detailsList.get(i);
            if (userDetails.toString().startsWith("Height")) {
                UserDetailsList.delete(i);
            }
            if (detailsList.get(i).toString().startsWith("BMI")) {
                UserDetailsList.delete(i);
            }
        }

        Scanner scanner = new Scanner(System.in);

        int newHeight = 0;
        int i = 0;
        while (i < 1) {
            System.out.println("Please input your new height (in cm): ");
            try {
                newHeight = Integer.parseInt(scanner.nextLine());
                if (newHeight >= 50 && newHeight <= 300) {
                    AddHeightCommand addHeightCommand = new
                            AddHeightCommand(newHeight, date, time);
                    addHeightCommand.execute();
                    int weight = GetWeightCommand.execute();
                    AddBMICommand addBMICommand = new AddBMICommand(newHeight, weight, date, time);
                    addBMICommand.execute();
                    System.out.println("You have successfully changed your height! " +
                            "You can continue to log your data!");
                    i++;
                } else {
                    System.out.println("Please input a positive integer between 50 and 300!");
                }
            } catch (NumberFormatException | ActiveEdgeException e) {
                System.out.println("Please input a positive integer between 50 and 300!");
            }
        }
    }
}
