package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.Event;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String contentInput) {
        super(contentInput);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.contentInput);
        taskList.addTask(event);
    }
}
