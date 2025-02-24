package shinpaimax.command;

import shinpaimax.storage.Storage;
import shinpaimax.task.Deadline;
import shinpaimax.task.TaskList;
import shinpaimax.ui.Ui;

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
