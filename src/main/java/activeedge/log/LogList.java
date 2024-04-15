package activeedge.log;
import java.util.ArrayList;

/**
 * The LogList class manages a list of Log objects.
 * It provides methods to add, retrieve, delete, and clear logs from the list.
 */
public class LogList {
    public static final ArrayList<Log> logList = new ArrayList<Log>();

    /**
     * Removes and returns the log at the specified index from the log list.
     *
     * @param index the index of the log to be removed
     * @return the log that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of the valid range
     */
    public static Log delete (int index) {
        if (index >= 0 && index < logList.size()) {
            return logList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    /**
     * Adds a new Log object to the log list.
     *
     * @param log the Log object to be added to the list
     */
    public static void add(Log log) {
        logList.add(log);
    }

    /**
     * Retrieves the first Log from the log list.
     *
     * @return the first Log object in the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public static Log get() {
        int index = 0;
        if (index >= 0 && index < logList.size()) {
            return logList.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    /**
     * Clears all entries from the log list. After calling this method,
     * the log list will be empty.
     */
    public static void clearLogs() {
        logList.clear();
    }
}
