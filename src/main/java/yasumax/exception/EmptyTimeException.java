package yasumax.exception;

import yasumax.datetime.Datetime;

/**
 * Handle missing or space-only time field for Task initialisation.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class EmptyTimeException extends YasuMaxException {
    public EmptyTimeException() {
        super("Time must not be empty for task insertion!" + Datetime.getFullDateTime());
    }
}
