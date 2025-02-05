package steadylah.command;

import steadylah.exception.InvalidIntegerException;
import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class UnmarkCommand extends Command {
    /**
     * Instantiate an unmark command which calls TaskList::unmarkTask via execute method.
     * @param contentInput raw end-user String content.
     */
    public UnmarkCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
        } catch (InvalidIntegerException e) {
            throw new InvalidIntegerException(this.contentInput);
        }
    }
}
