package steadylah.exception;

public class EmptyTimeException extends RuntimeException {
    public EmptyTimeException() {
        super("Time must not be empty for task insertion!");
    }
}
