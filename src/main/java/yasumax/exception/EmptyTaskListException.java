package yasumax.exception;

/**
 * Handle missing taskList or taskList whose size is illegally accessed on stripping it.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EmptyTaskListException extends YasuMaxException {
    public EmptyTaskListException() {
        super("No tasks found! Start planning something and gather your roses while ye may!");
    }
}
