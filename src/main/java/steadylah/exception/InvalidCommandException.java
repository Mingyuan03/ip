package steadylah.exception;

import steadylah.command.Help;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("You typed an invalid command!\n" + Help.getFullHelp());
    }
    public InvalidCommandException(Help help) {
        super(help.getSpecificHelp());
    }
}
