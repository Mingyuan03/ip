package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.Event;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.contentInput);
        taskList.addTask(event);
    }
}
