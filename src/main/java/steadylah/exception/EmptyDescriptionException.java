package steadylah.exception;

/**
 * Handle missing or space-only description field for Task initialisation.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EmptyDescriptionException extends RuntimeException {
    public EmptyDescriptionException() {
        super("Description must not be empty for task insertion!");
    }
}
