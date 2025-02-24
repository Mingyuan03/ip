package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ListCommand extends Command {
    /**
     * Instantiate new list command calling TaskList::printTasks with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public ListCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        if (!contentInput.isEmpty()) {
            System.out.println("Note that you don't need to list anything next time!");
        }
        return taskList.printTasks();
    }
}
