package steadylah.exception;

public class InvalidIntegerException extends RuntimeException {
    public InvalidIntegerException(String contentInput) {
        super("You typed a non positive integer: " + contentInput);
    }
}
