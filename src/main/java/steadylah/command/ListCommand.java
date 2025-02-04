package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!contentInput.isEmpty()) {
            System.out.println("Note that you don't need to list anything next time!");
        }
        taskList.printTasks();
    }
}
