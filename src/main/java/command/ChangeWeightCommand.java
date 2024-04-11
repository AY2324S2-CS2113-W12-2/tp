package command;

import activeedge.userdetails.UserDetails;
import activeedge.userdetails.UserDetailsList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static activeedge.Storage.saveLogsToFile;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class ChangeWeightCommand {


    public static void execute() throws ActiveEdgeException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        for (int i = 0; i < detailsList.size(); i++) {
            UserDetails userDetails = UserDetailsList.detailsList.get(i);
            if (userDetails.toString().startsWith("Weight")) {
                UserDetailsList.delete(i);
            }
            if (detailsList.get(i).toString().startsWith("BMI")) {
                UserDetailsList.delete(i);
            }
        }

        Scanner scanner = new Scanner(System.in);

        int newWeight = 0;
        int i = 0;
        while(i < 1) {
            System.out.println("Please input your weight (in kg): ");
            try {
                newWeight = Integer.parseInt(scanner.nextLine());
                if (newWeight >= 1 && newWeight <= 700) {
                    AddWeightCommand addWeightCommand = new
                            AddWeightCommand(newWeight, date, time);
                    addWeightCommand.execute();
                    System.out.println("You have successfully changed your height! You can continue logging data!");
                    saveLogsToFile("data/data.txt");
                    int height = GetHeightCommand.execute();
                    AddBMICommand addBMICommand = new AddBMICommand(height, newWeight, date, time);
                    addBMICommand.execute();
                    i++;
                } else {
                    System.out.println("Please input a positive integer between 1 and 700!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please input a positive integer between 1 and 700!");
            }
        }
    }
}
