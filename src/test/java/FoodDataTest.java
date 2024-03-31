import activeedge.FoodData;


public class FoodDataTest {

    public static void main(String[] args) {
        testFoodItemsValidity();
        testAppendItem();
    }

    public static void testFoodItemsValidity() {
        System.out.println("Testing food items validity...");
        FoodData.printFood();
    }

    public static void testAppendItem() {
        System.out.println("Testing appendItem method...");

        // Create a new item to append
        String[] newItem = {"new food item", "300"};

        // Append the new item to the foodItems array
        FoodData.foodItems = FoodData.appendItem(FoodData.foodItems, newItem);

        // Print the updated food items array
        System.out.println("Updated Food Items:");
        for (String[] foodItem : FoodData.foodItems) {
            System.out.println("Food Name: " + foodItem[0]);
            System.out.println("Calories: " + foodItem[1]);
            System.out.println(); // Empty line for readability
        }
    }
}
