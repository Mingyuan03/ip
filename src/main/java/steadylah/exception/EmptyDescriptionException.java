package steadylah.exception;

public class EmptyDescriptionException extends RuntimeException {
    public EmptyDescriptionException() {
        super("Description must not be empty for task insertion!");
    }
}
