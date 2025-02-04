package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String contentInput) {
        super(contentInput);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printSingleTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
    }
}
