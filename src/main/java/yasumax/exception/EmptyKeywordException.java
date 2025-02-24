package yasumax.exception;

/**
 * Handle missing or space-only keyword field for TaskList search by keyword.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EmptyKeywordException extends YasuMaxException {
    public EmptyKeywordException() {
        super("Keyword must not be empty for task search!");
    }
}
