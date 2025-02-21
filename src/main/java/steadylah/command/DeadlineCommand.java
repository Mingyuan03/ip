package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.Deadline;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class DeadlineCommand extends Command {
    /**
     * Instantiate a deadline command which calls TaskList::askTask via execute method.
     * @param contentInput raw end-user String content.
     */
    public DeadlineCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.contentInput);
        return taskList.addTask(deadline);
    }
}
