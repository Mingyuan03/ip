package yasumax.command;

import yasumax.storage.Storage;
import yasumax.task.TaskList;
import yasumax.ui.Ui;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public abstract class Command {
    protected String contentInput;

    /**
     * Each Command subclass inherits contentInput no matter its use case. 2 reasons:
     * 1. Command instances are made only on running CommandProcessor::parseCommand, wherein O(n)-time
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

    /**
     * Manipulate taskList's state in-place with user's text String.
     * @param taskList Underlying List-like DS for dynamic task scheduling.
     * @param ui Read-write IO operations and custom text rendering in primarily the CLI-mode.
     * @param storage Cache load and save handling at start and normal termination of program, but not the cache itself.
     * @return In-place taskList manipulation with curried user's text String.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
