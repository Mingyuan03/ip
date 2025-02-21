package steadylah.command;

import steadylah.exception.InvalidIntegerException;
import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class FindIndexCommand extends Command {
    /**
     * Instantiate a find-by-index command which calls TaskList::findSingleTask via execute method.
     * @param contentInput raw end-user String content.
     */
    public FindIndexCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return taskList.printSingleTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(this.contentInput);
        }
    }
}
