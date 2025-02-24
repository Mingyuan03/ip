package yasumax.task;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public abstract class Task {
    protected String description; // protected so that it's accessible in subclasses only for Task instantiation.
    private boolean isDone;

    // private static final Pattern ISO8601PATTERN = Pattern.compile("^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])");

    /**
     * Access user's description text String, to be inherited by concrete Task subclasses.
     * @return User's description text String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Char-find relevant tasks in TaskList, abstractified out in Task superclass for polymorphism.
     * @param keyword Predicted user's description text String as a key in TaskList::printRelevantTasks.
     * @return Boolean status indicating if each Task instance has matching description text String.
     */
    public boolean isFoundKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Access task completion status given private access modifier of isDone field for encapsulation,
     * actually used in 5 TaskList operations.
     * @return Boolean task completion status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Swap task completion status, abstractified out in Task superclass for polymorphism.
     */
    public void toggle() {
        this.isDone = !this.isDone;
    }

    /**
     * Distinguish each concrete Task subclass' instance from other Task subclasses' instances.
     * @return Unique icon identifier foreach Deadline instance.
     */
    public abstract char getTypeIcon();

    /**
     * Format task completion status, abstractifiable given boolean primitive type of isDone field.
     * @return Character icon indicating task completion status.
     */
    public char getStatusIcon() {
        return this.isDone ? 'X' : ' ';
    }
}
