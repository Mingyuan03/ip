import java.util.function.BiConsumer;

public enum Command {
    DEADLINE("deadline", (input, taskLogs) -> {
        Deadline deadline = new Deadline(input);
        taskLogs.addTask(deadline);
    }),
    EVENT("event", (input, taskLogs) -> {
        Event event = new Event(input);
        taskLogs.addTask(event);
    }),
    TODO("todo", (input, taskLogs) -> {
        ToDo todo = new ToDo(input);
        taskLogs.addTask(todo);
    }),
    LIST("list", (input, taskLogs) -> taskLogs.printTasks()),
    MARK("mark", (input, taskLogs) ->
            taskLogs.markTask(Integer.parseInt(input.trim().split(" ")[0]))),
    UNMARK("unmark", (input, taskLogs) ->
            taskLogs.unmarkTask(Integer.parseInt(input.trim().split(" ")[0]))),
    DELETE("delete", (input, taskLogs) ->
            taskLogs.deleteTask(Integer.parseInt(input.trim().split(" ")[0]))),
    HELP("help", (input, taskLogs) -> {
        System.out.println("You sought help! "
                + "As Dumbledore says, help will always be given to those who ask for it");
        System.out.println(Help.getFullHelp());
    }),
    UNKNOWN("", (input, taskLogs) -> {
        System.out.println("You typed an invalid command!");
        System.out.println(new InvalidCommandException().getMessage());
    });

    private final String header;
    private final BiConsumer<String, TaskLogs> action;

    Command(String newHeader, BiConsumer<String, TaskLogs> newAction) {
        this.header = newHeader;
        this.action = newAction;
    }

    public static Command parseHeader(String header) {
        for (Command command : Command.values()) {
            if (command.header.equals(header)) {
                return command;
            }
        }
        return UNKNOWN;
    }

    public void execute(String input, TaskLogs taskLogs) {
        this.action.accept(input, taskLogs);
    }
}
