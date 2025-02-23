package steadylah.exception;

/**
 * Handle missing or space-only time field for Task initialisation.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EmptyTimeException extends SteadyLahException {
    public EmptyTimeException() {
        super("Time must not be empty for task insertion!");
    }
}
