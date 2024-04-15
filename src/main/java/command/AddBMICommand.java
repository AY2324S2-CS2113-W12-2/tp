package command;

import activeedge.userdetails.LogBMI;
import static activeedge.userdetails.UserDetailsList.detailsList;

/**
 * This class represents a command to add a Body Mass Index (BMI) log entry to the user's health record.
 * It calculates BMI based on provided height and weight, logs it, and provides feedback on the BMI category.
 */
public class AddBMICommand {
    protected Integer heightInput;
    protected Integer weightInput;
    protected String date;
    protected String time;

    /**
     * Constructs an AddBMICommand with the specified user measurements and datetime.
     *
     * @param heightInput the height of the user in centimeters
     * @param weightInput the weight of the user in kilograms
     * @param date the date of the BMI recording
     * @param time the time of the BMI recording
     */
    public AddBMICommand (Integer heightInput, Integer weightInput, String date, String time) {
        this.heightInput = heightInput;
        this.weightInput = weightInput;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to calculate and log the BMI.
     * It calculates the BMI based on the height and weight provided,
     * logs the BMI along with the date and time, and prints out the BMI and a health range message.
     *
     * @throws ActiveEdgeException if there is an error during the execution of the command.
     */
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
