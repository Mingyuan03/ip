package steadylah.task;

import steadylah.exception.EmptyContentException;

public class ToDo extends Task {
    public ToDo(String toDoString) {
        super.description = toDoString.trim();
        if (super.description.isEmpty()) {
            throw new EmptyContentException();
        }
    }

    @Override
    public char getTypeIcon() {
        return 'T';
    }
}
