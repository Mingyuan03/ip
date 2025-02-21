package steadylah.exception;

public class InvalidIndexException extends RuntimeException {
    public InvalidIndexException(int index) {
        super("You typed an invalid index: " + index);
    }
}
