package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String contentInput) {
        super(contentInput);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
    }
}
