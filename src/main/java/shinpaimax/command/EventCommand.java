package shinpaimax.command;

import shinpaimax.storage.Storage;
import shinpaimax.task.Event;
import shinpaimax.task.TaskList;
import shinpaimax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EventCommand extends Command {
    /**
     * Instantiate an event command which calls TaskList::deleteTask via execute method.
     * @param contentInput raw end-user String content.
     */
    public EventCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.contentInput);
        return taskList.addTask(event);
    }
}
