package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.Deadline;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.contentInput);
        taskList.addTask(deadline);
    }
}
