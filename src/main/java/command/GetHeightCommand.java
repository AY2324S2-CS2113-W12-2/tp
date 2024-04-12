package command;

import activeedge.userdetails.UserDetails;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class GetHeightCommand{

    public static int execute() {
        int height = 0;
        for (UserDetails userDetails : detailsList) {
            String heightString = userDetails.toString();
            if (heightString.startsWith("Height")) {
                String[] items = heightString.split(" ");
                height = Integer.parseInt(items[1]);
            }
        }
        return height;
    }
}
