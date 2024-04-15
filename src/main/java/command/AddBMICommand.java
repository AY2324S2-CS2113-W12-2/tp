package command;

import activeedge.userdetails.LogBMI;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class AddBMICommand {
    protected Integer heightInput;
    protected Integer weightInput;
    protected String date;
    protected String time;

    public AddBMICommand (Integer heightInput, Integer weightInput, String date, String time) {
        this.heightInput = heightInput;
        this.weightInput = weightInput;
        this.date = date;
        this.time = time;
    }

    public void execute() throws ActiveEdgeException {
        double heightMeters = (double) heightInput / 100;
        int bmi = (int) (weightInput / (heightMeters * heightMeters));
        LogBMI logBMI = new LogBMI(bmi, date, time);
        detailsList.add(logBMI);
        System.out.println("Your BMI is " + bmi + ".");
        if (bmi < 19) {
            System.out.println("You are in the underweight range.");
        } else if (bmi < 25) {
            System.out.println("You are in the healthy weight range.");
        } else if (bmi < 30) {
            System.out.println("You are in the overweight range.");
        } else {
            System.out.println("You are in the obese weight range.");
        }
    }
}
