package shinpaimax.task;

import shinpaimax.exception.EmptyDescriptionException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class ToDo extends Task {
    /**
     * Instantiate a ToDo object.
     * @param toDoString raw end-user String input comprising description only.
     */
    public ToDo(String toDoString) {
        super.description = toDoString.trim();
        if (super.description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    @Override
    public char getTypeIcon() {
        return 'T';
    }
}
