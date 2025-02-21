package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.task.ToDo;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ToDoCommand extends Command {
    /**
     * Instantiate a todo command which calls TaskList::toDo via execute method.
     * @param contentInput raw end-user String content.
     */
    public ToDoCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo todo = new ToDo(this.contentInput);
        return taskList.addTask(todo);
    }
}
