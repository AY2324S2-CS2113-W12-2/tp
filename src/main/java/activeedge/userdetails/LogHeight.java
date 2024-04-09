package activeedge.userdetails;

import java.time.LocalDateTime;

/**
 * The LogHeight class represents a log of height details for a user.
 * It extends the UserDetails class and includes a timestamp for when the height was recorded.
 */
public class LogHeight extends UserDetails {

    /**
     * The date and time when the height was recorded.
     */
    private String date;
    private String time;

    /**
     * Constructs a LogHeight object with the specified height value and timestamp.
     *
     * @param value    The height value.
     * @param dateTime The date and time when the height was recorded.
     */
    public LogHeight(Integer value, String date, String time) {
        super(value);
        this.date = date;
        this.time = time;
    }

    /**
     * Gets the date and time when the height was recorded.
     *
     * @return The date and time when the height was recorded.
     */
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public LocalTime getTime() {
//        return time;
//    }

    /**
     * Returns a string representation of the LogHeight object.
     * This includes the height value and the timestamp.
     *
     * @return A string representation of the LogHeight object.
     */
    @Override
    public String toString() {
        return "Height " + this.getValue() + " cm" + " (Recorded on: " + date + " " + time + ")";
    }
}
