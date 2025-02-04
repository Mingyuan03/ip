package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.ToDo;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class ToDoCommand extends Command {
    public ToDoCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo todo = new ToDo(this.contentInput);
        taskList.addTask(todo);
    }
}
