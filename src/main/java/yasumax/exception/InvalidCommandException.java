package yasumax.exception;

import yasumax.command.Help;

/**
 * Handle invalid end-user command even after case-insensitivity.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class InvalidCommandException extends YasuMaxException {
    public InvalidCommandException() {
        super("You typed an invalid command!\n\n" + Help.getFullHelp());
    }
    public InvalidCommandException(Help help) {
        super("You typed an invalid command for " + help + ". It should be:" + help.getSpecificHelp());
    }
}
