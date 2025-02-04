package steadylah.exception;

public class EmptyKeywordException extends RuntimeException {
    public EmptyKeywordException() {
        super("Keyword must not be empty for task search!");
    }
}
