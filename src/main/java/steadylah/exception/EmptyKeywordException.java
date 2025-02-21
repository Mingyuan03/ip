package steadylah.exception;

/**
 * Handle missing or space-only keyword field for TaskList search by keyword.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EmptyKeywordException extends RuntimeException {
    public EmptyKeywordException() {
        super("Keyword must not be empty for task search!");
    }
}
