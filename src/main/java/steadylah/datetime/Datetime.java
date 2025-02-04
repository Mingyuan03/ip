package steadylah.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

public enum Datetime {
    // Datetime formats below
    ISO8601("yyyy-MM-dd'T'HH:mm"), // hyphenated date
    SPACING("yyyy-MM-dd HH:mm"), // spacing logically implies format has time
    DATETIME_SLASH("yyyy/MM/dd HH:mm"),
    DATETIME_TEXT("dd MMMM yyyy HH:mm"),
    DATETIME_TEXT_ABBREV("dd MMM yyyy HH:mm"),
    DATETIME_WEEKDAY("EEEE, yyyy-MM-dd HH:mm"),
    DATETIME_WEEKDAY_ABBREV("E, yyyy-MM-dd HH:mm"),
    DATETIME_COMPACT("yyyyMMdd HH:mm"),
    // Date-only formats below
    ISO8601_DATE("yyyy-MM-dd"),
    DATE_SLASH("yyyy/MM/dd"),
    DATE_TEXT("dd MMMM yyyy"),
    DATE_TEXT_ABBREV("dd MMM yyyy"),
    DATE_WEEKDAY("EEEE, yyyy-MM-dd"),
    DATE_WEEKDAY_ABBREV("E, yyyy-MM-dd"),
    DATE_COMPACT("yyyyMMdd");

    private final DateTimeFormatter formatter;

    Datetime(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public static Optional<LocalDateTime> parseDateTime(String timeString) {
        for (Datetime format : new Datetime[] {ISO8601, SPACING, DATETIME_SLASH, DATETIME_TEXT,
                DATETIME_TEXT_ABBREV, DATETIME_WEEKDAY, DATETIME_WEEKDAY_ABBREV, DATETIME_COMPACT}) {
            try {
                return Optional.of(LocalDateTime.parse(timeString, format.formatter));
            } catch (DateTimeException ignored) {}
        }
        return Optional.empty();
    }

    public static Optional<LocalDate> parseDate(String timeString) {
        for (Datetime format : new Datetime[] {ISO8601_DATE, DATE_SLASH, DATE_TEXT, DATE_TEXT_ABBREV,
                DATE_WEEKDAY, DATE_WEEKDAY_ABBREV, DATE_COMPACT}) {
            try {
                return Optional.of(LocalDate.parse(timeString, format.formatter));
            } catch (DateTimeException ignored) {}
        }
        return Optional.empty();
    }
}
