package activeedge.task;

public class Task {

    protected Integer value;
    protected String description;

    public Task(String description) {
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
