package yasumax.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import yasumax.command.Help;
import yasumax.datetime.Datetime;
import yasumax.exception.EmptyDescriptionException;
import yasumax.exception.EmptyTimeException;
import yasumax.exception.InvalidCommandException;
import yasumax.exception.InvalidDateTimeException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Instantiate new Event instance, defaulting to 00:00 for from date-only formats and 23:59 for to date-only formats
     * per academic "conventions".
     * @param eventString User's text String input, comprising description, from datetime and to datetime.
     */
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
        if (super.description.isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (startString.isEmpty() || endString.isEmpty()) {
            throw new EmptyTimeException();
        }
        // Default startTime chosen as 00:00, the most common startTime in academic settings
        this.startTime = Datetime.parseDateTime(startString)
                .or(() -> Datetime.parseDate(startString).map(LocalDate::atStartOfDay))
                .orElseThrow(() -> new InvalidDateTimeException());
        // Default endTime chosen as 23:59, the most common endTime in academic settings
        this.endTime = Datetime.parseDateTime(endString)
                .or(() -> Datetime.parseDate(endString).map(date -> date.atTime(23, 59)))
                .orElseThrow(() -> new InvalidDateTimeException());
    }

    /**
     * Format user's text String input to render back to user, abstractified out as a utility method.
     * @param eventDescription Raw user's text String input.
     * @return Formatted user's text String input.
     */
    public static String getRawDescription(String eventDescription) {
        String[] descriptionTime = eventDescription.split(" \\(from: ");
        String description = descriptionTime[0];
        String[] times = descriptionTime[1].split(" to: ");
        String fromTime = times[0];
        String toTime = times[1].substring(0, times[1].length() - 1);
        return description + " /from " + fromTime + " /to " + toTime;
    }

    /**
     * Reverse (unformat) Event::getRawDescription for events stored as formatted Strings in cache.
     * @return Raw user's text String input as ostensibly anticipated.
     */
    @Override
    public String getDescription() {
        return this.description + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Distinguish each Event instance from other Task subclasses' instances.
     * @return Unique icon identifier foreach Event instance.
     */
    @Override
    public char getTypeIcon() {
        return 'E';
    }
}
