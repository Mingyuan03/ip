package steadylah.command;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public enum Help {
    DEADLINE("<description> /by <time>"),
    EVENT("description> /from <time> /to <time>"),
    TODO("<description>"),
    DELETE("<index>"),
    MARK("<index>"),
    UNMARK("<index>"),
    FIND("<keyword>"),
    FIND_INDEX("<index>"),
    LIST,
    HELP,
    BYE;

    private final String commandFormat;

    Help(String commandFormat) {
        this.commandFormat = commandFormat;
    }

    Help() {
        this.commandFormat = null;
    }

    public String getSpecificHelp() {
        return this.commandFormat != null ? this.commandFormat : "No requirements to this command";
    }

    public static String getFullHelp() {
        StringBuilder fullHelp = new StringBuilder("Valid commands alongside their formats are:\n");
        for (Help help : Help.values()) {
            if (help.equals(Help.DEADLINE)) {
                fullHelp.append("FOR TASK INSERTION:\n");
            } else if (help.equals(DELETE)) {
                fullHelp.append("\nFOR TASK DELETION:\n");
            } else if (help.equals(MARK)) {
                fullHelp.append("\nFOR TASK ANNOTATION:\n");
            } else if (help.equals(FIND)) {
                fullHelp.append("\nFOR TASK SEARCH BY KEYWORD:\n");
            } else if (help.equals(FIND_INDEX)) {
                fullHelp.append("\nFOR TASK SEARCH BY INDEX:\n");
            } else if (help.equals(HELP)) {
                fullHelp.append("\nFOR MISCELLANEOUS FUNCTIONALITIES:\n");
            }
            fullHelp.append(help).append(":").append(help.getSpecificHelp());
            if (!help.equals(Help.BYE)) {
                fullHelp.append("\n");
            }
        }
        return fullHelp.toString();
    }
}
