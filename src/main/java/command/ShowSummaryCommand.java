package command;

import activeedge.log.Log;
import activeedge.userdetails.UserDetails;
import activeedge.ui.CommandUi;

import static activeedge.log.LogList.logList;
import static activeedge.userdetails.UserDetailsList.detailsList;
public class ShowSummaryCommand {
    public void execute() {
        int totalCalories = 0;
        int totalWaterIntake = 0;
        int totalCaloriesBurnt = 0;
        int height = 0;
        int weight = 0;
        int BMI = 0;

        String calorieGoal = "0";
        String waterGoal = "0";

        for (Log log : logList) {
            if (log.toString().startsWith("Meal")) {
                String[] parts = log.toString().split("\\s*\\|\\s*|\\s+");
                totalCalories += Integer.parseInt(parts[parts.length - 6]);
            } else if (log.toString().startsWith("Water")) {
                String[] parts = log.toString().split("\\s*\\|\\s*|\\s+");
                totalWaterIntake += Integer.parseInt(parts[1]);
            } else if (log.toString().startsWith("Exercise")) {
                String[] parts = log.toString().split("\\s*\\|\\s*|\\s+");
                totalCaloriesBurnt += Integer.parseInt(parts[parts.length - 6]);
            } else if (log.toString().startsWith("Goal")) {
                String[] parts = log.toString().split(" ");
                if (parts[1].equals("Calorie")) {
                    calorieGoal = parts[2];
                } else if (parts[1].equals("Water")) {
                    waterGoal = parts[2];
                }
            }
        }

        for (UserDetails userdetails : detailsList) {
            if (userdetails.toString().startsWith("Height")) {
                String[] parts = userdetails.toString().split(" ");
                height = Integer.parseInt(parts[1]);
            } else if (userdetails.toString().startsWith("Weight")) {
                String[] parts = userdetails.toString().split(" ");
                weight = Integer.parseInt(parts[1]);
            } else if (userdetails.toString().startsWith("BMI")) {
                String[] parts = userdetails.toString().split(" ");
                BMI = Integer.parseInt(parts[1]);
            }
        }

        int netCalories = totalCalories - totalCaloriesBurnt;
        String calorieStatus = calculateCalorieStatus(netCalories, Integer.parseInt(calorieGoal));

        CommandUi.printShowSummaryMessage(height, weight, BMI, totalCalories,totalWaterIntake, totalCaloriesBurnt,
                calorieGoal,waterGoal,netCalories, calorieStatus);
    }

    private String calculateCalorieStatus(int netCalories, int calorieGoal) {
        if (netCalories > calorieGoal) {
            return "Calories Surplus";
        } else if (netCalories < calorieGoal) {
            return "Calories Deficit";
        } else {
            return "Maintenance";
        }
    }
}
