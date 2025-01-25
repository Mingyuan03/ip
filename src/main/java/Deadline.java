public class Deadline extends Task {
    private final String byTime;

    public Deadline(String deadlineString) {
        int byIndex = deadlineString.indexOf(" /by ");
        if (byIndex == -1) {
            // Check existence of 1st instance of by as a separate word.
            throw new IllegalArgumentException("Correct Format: <description> /by <time>");
        }
        super.description = deadlineString.substring(0, byIndex);
        this.byTime = deadlineString.substring(byIndex + " /by ".length());
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + this.byTime + ")";
    }

    @Override
    public char getTypeIcon() {
        return 'D';
    }
}
