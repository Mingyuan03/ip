package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.task.ToDo;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ToDoCommand extends Command {
    /**
     * Instantiate new todo command calling TaskList::addTask with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public ToDoCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        ToDo todo = new ToDo(this.contentInput);
        return taskList.addTask(todo);
    }
}
