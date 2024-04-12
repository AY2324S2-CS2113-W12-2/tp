package command;

import activeedge.userdetails.UserDetails;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class GetWeightCommand {

    public static int execute() {
        int weight = 0;
        for (UserDetails details : detailsList) {
            String weightString = details.toString();
            if (weightString.startsWith("Weight")){
                String[] items = weightString.split(" ");
                weight = Integer.parseInt(items[1]);
                //System.out.println("Weight: " + weight + " kg");
            }
        }
        return weight;
    }
}

