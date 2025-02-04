package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String contentInput) {
        super(contentInput);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
    }
}
