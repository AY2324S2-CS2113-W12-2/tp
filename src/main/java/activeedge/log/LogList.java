package activeedge.log;
import java.util.ArrayList;

public class LogList {
    // Static constant ArrayList to store Task objects
    public static final ArrayList<Log> logList = new ArrayList<Log>();

    public static Log delete (int index) {
        if (index >= 0 && index < logList.size()) {
            return logList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public static void add(Log log) {
        logList.add(log);
    }

    public static Log get() {
        int index = 0;
        if (index >= 0 && index < logList.size()) {
            return logList.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public static void clearTasks() {
        logList.clear();
    }

}
