package steadylah.task;

public abstract class Task {
    protected String description; // protected so that it's accessible in subclasses only.
    private boolean isDone; // protected so that it's accessible in subclasses only.

    // private static final Pattern ISO8601PATTERN = Pattern.compile("^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])")

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }

    abstract public char getTypeIcon();

    public char getStatusIcon() {
        return this.isDone ? 'X' : ' ';
    }
}
