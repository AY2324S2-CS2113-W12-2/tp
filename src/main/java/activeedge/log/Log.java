package activeedge.log;

public class Log {

    protected Integer value;
    protected String description;

    public Log (String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
