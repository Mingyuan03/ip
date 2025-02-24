package yasumax.exception;

/**
 * Handle non-numeric index field for TaskList methods inputting an index.
 * Negative, floating-point or out-of-bound numeric values are explicitly handled by existing builtin exceptions,
 * hence it is a designer choice here to omit them from this exception's scope/implementation.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class InvalidIntegerException extends YasuMaxException {
    public InvalidIntegerException(String contentInput) {
        super("You typed a non integer: " + contentInput);
    }
}
