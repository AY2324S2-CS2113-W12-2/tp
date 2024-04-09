package command;

import activeedge.userdetails.LogWeight;
import static activeedge.userdetails.UserDetailsList.detailsList;
import java.time.LocalDateTime;


public class AddWeightCommand {
    protected Integer weight;
    protected String date;
    protected String time;


    public AddWeightCommand(Integer weight, String date, String time) {
        this.weight = weight;
        this.date = date;
        this.time = time;
    }

    public void execute() throws ActiveEdgeException {
        LogWeight logWeight = new LogWeight(weight, date, time);
        detailsList.add(logWeight);
    }
}

