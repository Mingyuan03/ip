package steadylah.exception;

public class EmptyContentException extends RuntimeException {
    public EmptyContentException() {
        super("Description and/or time must not be empty for task insertion!");
    }
}
