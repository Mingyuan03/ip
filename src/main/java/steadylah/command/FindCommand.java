package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class FindCommand extends Command {
    /**
     * Instantiate a find-by-keyword command which calls TaskList::findRelevantTasks via execute method.
     * @param contentInput raw end-user String content.
     */
    public FindCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printRelevantTasks(this.contentInput);
    }
}
