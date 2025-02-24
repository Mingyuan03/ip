package yasumax.task;

import yasumax.exception.EmptyDescriptionException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ToDo extends Task {
    /**
     * Instantiate new ToDo instance.
     * @param toDoString User's text String input, comprising description only.
     */
    public ToDo(String toDoString) {
        super.description = toDoString.trim();
        if (super.description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Distinguish each ToDo instance from other Task subclasses' instances.
     * @return Unique icon identifier foreach ToDo instance.
     */
    @Override
    public char getTypeIcon() {
        return 'T';
    }
}
