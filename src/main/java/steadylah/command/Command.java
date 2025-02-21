package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public abstract class Command {
    protected String contentInput;

    /**
     * Each Command subclass inherits contentInput no matter its use case. 2 reasons:
     * 1. Command instances are made only on running CommandParser::parseCommand, wherein O(n)-time
     * computationally expensive string split is done; repeating it elsewhere for commands needing
     * contentInput is inefficient and counterintuitive.
     * 2. Future versions of commands yet needing contentInput may require it. E.g. developers may
     * extend list or help functionality for more granular assistance; keeping contentInput enables
     * much-needed backward compatibility as and when this surfaces.
     * @param contentInput processed user input stripping header command.
     */
    public Command(String contentInput) {
        this.contentInput = contentInput;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
