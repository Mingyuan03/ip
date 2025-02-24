package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ClearCommand extends Command {
    /**
     * Instantiate new delete command calling TaskList::deleteAllTasks with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public ClearCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        return taskList.deleteAllTasks();
    }
}
