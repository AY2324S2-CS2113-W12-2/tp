package command;

import activeedge.userdetails.LogHeight;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class AddHeightCommand {
    protected Integer height;
    protected String date;
    protected String time;

    public AddHeightCommand (Integer height, String date, String time) {
        this.height = height;
        this.date = date;
        this.time = time;
    }

    public void execute() throws ActiveEdgeException {
        LogHeight logHeight = new LogHeight(height,date, time);
        detailsList.add(logHeight);
    }
}
