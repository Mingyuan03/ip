package steadylah.task;

import steadylah.datetime.Datetime;
import steadylah.command.Help;
import steadylah.exception.EmptyContentException;
import steadylah.exception.InvalidCommandException;

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
        String fromTime = times[0];
        String toTime = times[1].substring(0, times[1].length() - 1);
        return description + " /from " + fromTime + " /to " + toTime;
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
        this.startTime = Datetime.parseDateTime(startString).
                or(() -> Datetime.parseDate(startString).map(LocalDate::atStartOfDay)).
                orElseThrow(() -> new DateTimeException("Invalid datetime format"));
        // Default endTime chosen as 23:59, the most common endTime in academic settings
        this.endTime = Datetime.parseDateTime(endString).
                or(() -> Datetime.parseDate(endString).map(date -> date.atTime(23, 59))).
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
