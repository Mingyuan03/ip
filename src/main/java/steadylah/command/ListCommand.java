package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ListCommand extends Command {
    /**
     * Instantiate a list command which calls TaskList::printTasks via execute method.
     * @param contentInput raw end-user String content.
     */
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
