package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.Event;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EventCommand extends Command {
    /**
     * Instantiate new event command calling TaskList::addTask with user's text String in this::execute.
     * @param contentInput User's text String.
     */
    public EventCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) { // Refer to Command superclass for Javadoc.
        Event event = new Event(this.contentInput);
        return taskList.addTask(event);
    }
}
