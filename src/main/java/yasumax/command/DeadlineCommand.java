package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.Deadline;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class DeadlineCommand extends Command {
    /**
     * Instantiate new deadline command calling TaskList::addTask with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public DeadlineCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        Deadline deadline = new Deadline(this.contentInput);
        return taskList.addTask(deadline);
    }
}
