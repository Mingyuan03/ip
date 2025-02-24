package shinpaimax.exception;

/**
 * Handle IndexOutOfBound builtin exception for TaskList methods inputting an index.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class InvalidIndexException extends ShinpaiMaxException {
    public InvalidIndexException(int index) {
        super("You typed an invalid index: " + index);
    }
}
