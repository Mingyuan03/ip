package shinpaimax.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import shinpaimax.command.Help;
import shinpaimax.datetime.Datetime;
import shinpaimax.exception.EmptyDescriptionException;
import shinpaimax.exception.EmptyTimeException;
import shinpaimax.exception.InvalidCommandException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Deadline extends Task {
    private final LocalDateTime byTime;

    /**
     * Instantiate a Deadline object.
     * @param deadlineString raw end-user String input comprising description and byTime.
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

    public static String getRawDescription(String deadlineDescription) {
        String[] descriptionTime = deadlineDescription.split(" \\(by: ");
        String description = descriptionTime[0];
        String byTime = descriptionTime[1].substring(0, descriptionTime[1].length() - 1);
        return description + " /by " + byTime;
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
