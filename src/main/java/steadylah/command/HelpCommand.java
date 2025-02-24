package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class HelpCommand extends Command {
    /**
     * Instantiate a help command which prints general/specific help via execute method.
     * @param contentInput raw end-user String content.
     */
    public HelpCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder helpResponse = new StringBuilder("Steady bro! Help is on the way!\n\n");
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
