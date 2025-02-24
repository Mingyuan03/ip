package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class HelpCommand extends Command {
    /**
     * Instantiate new help command bypassing TaskList to prints full xor specific help in this::execute.
     * @param contentInput User's text String.
     */
    public HelpCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        StringBuilder helpResponse = new StringBuilder(new Ui().getGreeting(true))
                .append("I am here to assist you.")
                .append("\n\n");
        boolean isSpecificHelp = false;
        for (Help help : Help.values()) {
            if (this.contentInput.equals(help.toString().toLowerCase())) {
                helpResponse.append(help).append(":").append(help.getSpecificHelp());
                isSpecificHelp = true;
                break; // Adaptive search
            }
        }
        if (!isSpecificHelp) {
            helpResponse.append(Help.getFullHelp());
        }
        return helpResponse.toString();
    }
}
