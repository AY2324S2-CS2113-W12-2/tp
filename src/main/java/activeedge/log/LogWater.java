package activeedge.log;

/**
 * Represents a task related to water intake.
 * */
public class LogWater extends Log {
    private int quantity;
    private String date;
    private String time;


    /**
     * Constructs a LogWater object with the specified quantity.
     *
     * @param quantity The quantity of water intake.
     */
    public LogWater (int quantity, String date, String time) {
        super("Water intake");
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    /**
     * Gets the quantity of water intake.
     *
     * @return The quantity of water intake.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns a string representation of the LogWater object.
     *
     * @return A string representing the water task and its quantity.
     */
    @Override
    public String toString() {
        return "Water" + " | " +this.getQuantity() + " ml"
                + " (Recorded on: " + date + " " + time + ")";
    }
}
