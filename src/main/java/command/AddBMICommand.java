package command;

import activeedge.userdetails.LogBMI;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class AddBMICommand {
    protected Integer bmi;
    protected String date;
    protected String time;

    public AddBMICommand (Integer bmi, String date, String time) {
        this.bmi = bmi;
        this.date = date;
        this.time = time;
    }

    public void execute() throws ActiveEdgeException {
        LogBMI logBMI = new LogBMI(bmi, date, time);
        detailsList.add(logBMI);
    }
}
