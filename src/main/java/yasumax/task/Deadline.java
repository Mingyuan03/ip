package yasumax.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import yasumax.command.Help;
import yasumax.datetime.Datetime;
import yasumax.exception.EmptyDescriptionException;
import yasumax.exception.EmptyTimeException;
import yasumax.exception.InvalidCommandException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Deadline extends Task {
    private final LocalDateTime byTime;

    /**
     * Instantiate new Deadline instance, defaulting to 23:59 for date-only formats per academic "conventions".
     * @param deadlineString User's text String input, comprising description and deadline.
     */
    public Deadline(String deadlineString) {
        int byIndex = deadlineString.indexOf(" /by ");
        if (byIndex == -1) {
            // Check existence of 1st instance of by as a separate word.
            throw new InvalidCommandException(Help.DEADLINE);
        }
        super.description = deadlineString.substring(0, byIndex).trim();
        String byString = deadlineString.substring(byIndex + " /by ".length()).trim();
        if (super.description.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (byString.isEmpty()) {
            throw new EmptyTimeException();
        }
        // Default byTime chosen as 23:59, the most common deadline in academic settings
        this.byTime = Datetime.parseDateTime(byString)
                .or(() -> Datetime.parseDate(byString).map(date -> date.atTime(23, 59)))
                .orElseThrow(() -> new DateTimeException("Invalid datetime format"));
    }

    /**
     * Format user's text String input to render back to user, abstractified out as a utility method.
     * @param deadlineDescription Raw user's text String input.
     * @return Formatted user's text String input.
     */
    public static String getRawDescription(String deadlineDescription) {
        String[] descriptionTime = deadlineDescription.split(" \\(by: ");
        String description = descriptionTime[0];
        String byTime = descriptionTime[1].substring(0, descriptionTime[1].length() - 1);
        return description + " /by " + byTime;
    }

    /**
     * Reverse (unformat) Deadline::getRawDescription for deadlines stored as formatted Strings in cache.
     * @return Raw user's text String input as ostensibly anticipated.
     */
    @Override
    public String getDescription() {
        return this.description + " (by: " + this.byTime + ")";
    }

    /**
     * Distinguish each Deadline instance from other Task subclasses' instances.
     * @return Unique icon identifier foreach Deadline instance.
     */
    @Override
    public char getTypeIcon() {
        return 'D';
    }
}
