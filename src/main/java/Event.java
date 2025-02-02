import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DateTimeException;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    // ISO 8601 Datetime Standard: yyyy-MM-dd HH:mm

    public static String getRawDescription(String eventDescription) {
        String[] descriptionTime = eventDescription.split(" \\(from: ");
        String description = descriptionTime[0];
        String[] times = descriptionTime[1].split(" to: ");
        return description + " /from " + times[0] + " /to " + times[1];
    }

    public Event(String eventString) {
        int fromIndex = eventString.indexOf(" /from ");
        int toIndex = eventString.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            // Check existence of the 1st instances of from and to, wherein former precedes latter.
            throw new InvalidCommandException(Help.EVENT);
        }
        super.description = eventString.substring(0, fromIndex).trim();
        String startString = eventString.substring(fromIndex + " /from ".length(), toIndex).trim();
        String endString = eventString.substring(toIndex + " /to ".length()).trim();
        if (startString.isEmpty() || endString.isEmpty() || super.description.isEmpty()) {
            throw new EmptyContentException();
        }
        // Default startTime chosen as 00:00, the most common startTime in academic settings
        this.startTime = DateTimeFormat.parseDateTime(startString).
                or(() -> DateTimeFormat.parseDate(startString).map(LocalDate::atStartOfDay)).
                orElseThrow(() -> new DateTimeException("Invalid datetime format"));
        // Default endTime chosen as 23:59, the most common endTime in academic settings
        this.endTime = DateTimeFormat.parseDateTime(endString).
                or(() -> DateTimeFormat.parseDate(endString).map(date -> date.atTime(23, 59))).
                orElseThrow(() -> new DateTimeException("Invalid datetime format"));
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
