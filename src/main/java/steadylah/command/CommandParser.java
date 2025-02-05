package steadylah.command;

import steadylah.exception.InvalidCommandException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class CommandParser {
    /**
     * Process raw String input from end-user into header command and descriptive content.
     * @param input String detail defining specific TaskList behaviour.
     * @return Command with yet-processed String content.
     */
    public static Command parseCommand(String input) {
        String[] words = input.split(" ");
        String header = words[0].toLowerCase(); // Improve planner's robustness with case insensitivity
        String content = input.substring(header.length()).trim();
        return switch (header) {
        case "deadline" -> new DeadlineCommand(content);
        case "event" -> new EventCommand(content);
        case "todo" -> new ToDoCommand(content);
        case "delete" -> new DeleteCommand(content);
        case "mark" -> new MarkCommand(content);
        case "unmark" -> new UnmarkCommand(content);
        case "list" -> new ListCommand(content);
        case "find" -> new FindCommand(content);
        case "find_index" -> new FindIndexCommand(content);
        case "help" -> new HelpCommand(content);
        default -> throw new InvalidCommandException();
        };
    }
}
