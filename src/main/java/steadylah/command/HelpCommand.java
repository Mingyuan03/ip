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
        return "You sought help! "
                + "As Dumbledore says, help will always be given to those who ask for it!\n\n"
                + Help.getFullHelp();
    }
}
