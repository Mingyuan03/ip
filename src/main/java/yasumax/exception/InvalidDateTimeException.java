package yasumax.exception;

import yasumax.datetime.Datetime;

/**
 * Handle DateTimeException by incorporating the list of valid datetime recognised by bot.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class InvalidDateTimeException extends YasuMaxException {
    public InvalidDateTimeException() {
        super("Invalid datetime format. Check all formats as applied to time now:\n" + Datetime.getFullDateTime());
    }
}
