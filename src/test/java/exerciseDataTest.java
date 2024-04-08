import activeedge.ExerciseData;
public class exerciseDataTest {

    public static void main(String[] args) {

        testExerciseDataValidity();
    }

    public static void testExerciseDataValidity() {
        ExerciseData.assertExerciseData();

        // Custom test cases
        String[][] customData = {
                {"test exercise", "5"},
                {"", "10"}, // Empty exercise name
                {"custom exercise", "-5"}, // Negative calories burnt
                {"another exercise", "0"} // Zero calories burnt
        };

        System.out.println("Running custom test cases...");
        for (String[] exercise : customData) {
            try {
                System.out.println("Exercise Name: " + exercise[0]);
                System.out.println("Calories Burnt Per Minute: " + exercise[1]);
                System.out.println(); // Empty line for readability
                ExerciseData.exercisesList = new String[][]{exercise};
                ExerciseData.assertExerciseData(); // This should throw an AssertionError
            } catch (AssertionError e) {
                System.out.println("Assertion Error: " + e.getMessage());
                System.out.println(); // Empty line for readability
            }
        }
    }
}

