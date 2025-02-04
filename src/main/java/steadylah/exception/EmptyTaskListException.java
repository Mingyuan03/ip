package steadylah.exception;

public class EmptyTaskListException extends RuntimeException {
    public EmptyTaskListException() {
        super("No tasks found! Start planning something and gather your roses while ye may!");
    }
}
