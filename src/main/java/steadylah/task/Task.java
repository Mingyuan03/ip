package steadylah.task;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public abstract class Task {
    protected String description; // protected so that it's accessible in subclasses only.
    private boolean isDone; // protected so that it's accessible in subclasses only.

    // private static final Pattern ISO8601PATTERN = Pattern.compile("^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])")

    public String getDescription() {
        return this.description;
    }

    public boolean isFoundKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }

    public abstract char getTypeIcon();

    public char getStatusIcon() {
        return this.isDone ? 'X' : ' ';
    }
}
