package steadylah.task;

import steadylah.exception.EmptyDescriptionException;

public class ToDo extends Task {
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
