public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String eventString) {
        int fromIndex = eventString.indexOf(" /from ");
        int toIndex = eventString.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            // Check existence of the 1st instances of from and to, wherein former precedes latter.
            throw new IllegalArgumentException("Correct Format: <description> /from <time> /to <time>");
        }
        super.description = eventString.substring(0, fromIndex);
        this.startTime = eventString.substring(fromIndex + " /from ".length(), toIndex);
        this.endTime = eventString.substring(toIndex + " /to ".length());
    }

    @Override
    public String getDescription() {
        return this.description + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public char getTypeIcon() {
        return 'E';
    }
}
