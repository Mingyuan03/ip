package steadylah.command;

import steadylah.exception.InvalidIntegerException;
import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(Integer.parseInt(this.contentInput.trim().split(" ")[0]));
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(this.contentInput);
        }
    }
}
