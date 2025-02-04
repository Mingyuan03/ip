package steadylah.command;

public enum Help {
    DEADLINE ("<description> /by <time>"),
    EVENT ("description> /from <time> /to <time>"),
    TODO ("<description>"),
    DELETE ("<index>"),
    MARK ("<index>"),
    UNMARK ("<index>"),
    FIND ("<index>"),
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
                fullHelp.append("FOR TASK DELETION:\n");
            } else if (help.equals(MARK)) {
                fullHelp.append("FOR TASK ANNOTATION:\n");
            } else if (help.equals(FIND)) {
                fullHelp.append("FOR TASK SEARCH:\n");
            } else if (help.equals(HELP)) {
                fullHelp.append("FOR MISCELLANEOUS FUNCTIONALITIES:\n");
            }
            fullHelp.append(help).append(":");
            fullHelp.append(help.getSpecificHelp());
            if (!help.equals(Help.BYE)) {
                fullHelp.append("\n");
            }
        }
        return fullHelp.toString();
    }
}
