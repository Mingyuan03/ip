package shinpaimax.command;

import shinpaimax.storage.Storage;
import shinpaimax.task.TaskList;
import shinpaimax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ClearCommand extends Command {
    /**
     * Instantiate a delete command which calls TaskList::deleteTask via execute method.
     * @param contentInput raw end-user String content.
     */
    public ClearCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.deleteAllTasks();
    }
}
