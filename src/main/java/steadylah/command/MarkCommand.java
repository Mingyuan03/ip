package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
    }
}
