public abstract class Task {
    protected String description; // protected so that it's accessible in subclasses only.
    private boolean isDone; // protected so that it's accessible in subclasses only.

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
