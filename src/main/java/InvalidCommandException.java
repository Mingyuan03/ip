public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super(Help.getFullHelp());
    }
    public InvalidCommandException(Help help) {
        super(help.getSpecificHelp());
    }
}
