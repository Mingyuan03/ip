package steadylah.command;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import steadylah.exception.InvalidCommandException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class CommandProcessor {
    private static final Map<String, Class<? extends Command>> COMMAND_MAP = new HashMap<>(); // PECS

    static {
        COMMAND_MAP.put("deadline", DeadlineCommand.class);
        COMMAND_MAP.put("event", EventCommand.class);
        COMMAND_MAP.put("todo", ToDoCommand.class);
        COMMAND_MAP.put("delete", DeleteCommand.class);
        COMMAND_MAP.put("mark", MarkCommand.class);
        COMMAND_MAP.put("unmark", UnmarkCommand.class);
        COMMAND_MAP.put("list", ListCommand.class);
        COMMAND_MAP.put("find", FindCommand.class);
        COMMAND_MAP.put("find_index", FindIndexCommand.class);
        COMMAND_MAP.put("help", HelpCommand.class);
    }

    /**
     * Process raw String input from end-user into header command and descriptive content by more efficient Map DS in
     * amoritzed O(1)-time, enabling scalability if future project increments add many more commands to process per
     * dialogue, and allowing Java stream usage.
     * @param input String detail defining specific TaskList behaviour.
     * @return Command with yet-processed String content.
     * @throws NoSuchMethodException Command(String) constructor signature absent in Command subclass, sanity check that
     *      all concrete Command subclasses must inherit Command base class with this explicit signature.
     * @throws InvocationTargetException checked exception for if constructor or method call in-turn throws exception.
     * @throws InstantiationException sanity checked exception for if abstract Command subclass or interface is called.
     * @throws IllegalAccessException sanity checked exception for if constructor is inaccessible, likely by modifiers.
     */
    public static Command processCommandByMap(String input)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String[] words = input.split(" ");
        String header = words[0].toLowerCase(); // Improve planner's robustness with case insensitivity
        String content = input.substring(header.length()).trim();
        Class<? extends Command> commandClass = COMMAND_MAP.get(header);
        if (commandClass == null) {
            throw new InvalidCommandException();
        }
        try {
            return commandClass.getConstructor(String.class).newInstance(content);
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        } catch (Exception e) {
            assert !(e instanceof NoSuchMethodException)
                    : "Exception should not be NoSuchMethodException as abstract Command superclass has this signature";
            System.out.println("A wild exception occurred in processing Command by map.");
            throw e;
        }
    }
    /**
     * Process raw String input from end-user into header command and descriptive content by naive switch block in O(n).
     * @param input String detail defining specific TaskList behaviour.
     * @return Command with yet-processed String content.
     */
    public static Command processCommandBySwitch(String input) {
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
