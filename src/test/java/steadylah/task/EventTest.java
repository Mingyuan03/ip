package steadylah.task;

import steadylah.exception.EmptyDescriptionException;
import steadylah.exception.EmptyTimeException;
import steadylah.exception.InvalidCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void getDescription_fromDatetimeToDatetimeEvent_ISO8601Test() {
        Event event = new Event("MA2108S /from 2025-02-04T18:00 /to 2025-02-15T12:00");
        assertEquals("MA2108S (from: 2025-02-04T18:00 to: 2025-02-15T12:00)", event.getDescription());
    }

    @Test
    public void getDescription_fromDateOnlyToDatetimeEvent_ISO8601MidnightTest() {
        Event event = new Event("CS2101 /from Monday, 2025-02-03 /to 2025-02-10T15:00");
        assertEquals("CS2101 (from: 2025-02-03T00:00 to: 2025-02-10T15:00)", event.getDescription());
    }

    @Test
    public void getDescription_fromDatetimeToDateOnlyEvent_ISO8601MidnightTest() {
        Event event = new Event("CS2109S /from 2025-02-04T18:00 /to Sat, 2025-02-15");
        assertEquals("CS2109S (from: 2025-02-04T18:00 to: 2025-02-15T23:59)", event.getDescription());
    }

    @Test
    public void constructor_emptyDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new Event(" /from 2025-02-04T18:00 /to 2025-02-15"));
    }

    @Test
    public void constructor_emptyFromTime_throwsEmptyTimeException() {
        assertThrows(EmptyTimeException.class, () -> new Event("CS2109S /from  /to 2025-02-15T12:00"));
    }

    @Test
    public void constructor_emptyToTime_throwsEmptyTimeException() {
        assertThrows(EmptyTimeException.class, () -> new Event("CS2109S /from 2025-02-03T12:00 /to "));
    }

    @Test
    public void constructor_invalidEvent_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> new Event("IS1108 from 2025-02-04 to 2025-02-15"));
    }

    @Test
    public void getRawDescriptionUtility_datetimeEvent_ISO8601Test() {
        String description = "MA2108S /from 2025-02-04T18:00 /to 2025-02-15T12:00";
        assertEquals(description, Event.getRawDescription(new Event(description).getDescription()));
    }
}
