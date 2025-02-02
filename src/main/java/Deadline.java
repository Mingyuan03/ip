public class Deadline extends Task {
    private final String byTime;

    public static String getRawDescription(String deadlineDescription) {
        String[] descriptionTime = deadlineDescription.split(" \\(by: ");
        String description = descriptionTime[0];
        String byTime = descriptionTime[1].substring(0, descriptionTime[1].length() - 1);
        return description + " /by " + byTime;
    }

    public Deadline(String deadlineString) {
        int byIndex = deadlineString.indexOf(" /by ");
        if (byIndex == -1) {
            // Check existence of 1st instance of by as a separate word.
            throw new InvalidCommandException(Help.DEADLINE);
        }
        super.description = deadlineString.substring(0, byIndex).trim();
        this.byTime = deadlineString.substring(byIndex + " /by ".length()).trim();
        if (this.byTime.isEmpty() || super.description.trim().isEmpty()) {
            throw new EmptyContentException();
        }
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
