public class ToDo extends Task {
    public ToDo(String toDoString) {
        super.description = toDoString;
    }

    @Override
    public char getTypeIcon() {
        return 'T';
    }
}
