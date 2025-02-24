package shinpaimax.command;

import shinpaimax.exception.InvalidIntegerException;
import shinpaimax.storage.Storage;
import shinpaimax.task.TaskList;
import shinpaimax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class MarkCommand extends Command {
    /**
     * Instantiate a mark command which calls TaskList::markTask via execute method.
     * @param contentInput raw end-user String content.
     */
    public MarkCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return taskList.markTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(this.contentInput);
        }
    }
}
