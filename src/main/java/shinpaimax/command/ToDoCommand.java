package shinpaimax.command;

import shinpaimax.storage.Storage;
import shinpaimax.task.TaskList;
import shinpaimax.task.ToDo;
import shinpaimax.ui.Ui;

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
