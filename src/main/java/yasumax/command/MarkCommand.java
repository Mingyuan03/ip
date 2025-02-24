package yasumax.command;

import yasumax.exception.InvalidIntegerException;
import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class MarkCommand extends Command {
    /**
     * Instantiate new mark command calling TaskList::markTask with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public MarkCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        try {
            return taskList.markTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(this.contentInput);
        }
    }
}
