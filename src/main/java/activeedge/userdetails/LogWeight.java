package activeedge.userdetails;

/**
 * The LogWeight class represents a log of weight details for a user.
 * It extends the UserDetails class and includes a timestamp for when the weight was recorded.
 */
public class LogWeight extends UserDetails {

    /**
     * The date and time when the weight was recorded.
     */
    private String date;
    private String time;

    /**
     * Constructs a LogWeight object with the specified weight value and timestamp.
     *
     * @param value    The weight value.
     * @param date The date when the weight was recorded.
     * @param time The time when the weight was recorded.
     */
    public LogWeight(Integer value, String date, String time) {
        super(value);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a string representation of the LogWeight object.
     * This includes the weight value and the timestamp.
     *
     * @return A string representation of the LogWeight object.
     */
    @Override
    public String toString() {
        return "Weight " + this.getValue() + " kg" + " (Recorded on: " + date + " " + time + ")";
    }
}

