package yasumax.command;

import yasumax.exception.InvalidIntegerException;
import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class FindIndexCommand extends Command {
    /**
     * Instantiate new index-find command calling TaskList::findSingleTask with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public FindIndexCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        try {
            return taskList.printSingleTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(this.contentInput);
        }
    }
}
