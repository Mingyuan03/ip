package steadylah.task;

import steadylah.exception.EmptyDescriptionException;
import steadylah.exception.EmptyTimeException;
import steadylah.exception.InvalidCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void getDescription_datetimeDeadline_ISO8601Test() {
        Deadline deadline = new Deadline ("CS2103T /by 2025-02-07T16:00");
        assertEquals("CS2103T (by: 2025-02-07T16:00)", deadline.getDescription());
    }

    @Test
    public void getDescription_dateOnlyDeadline_ISO8601MidnightTest() {
        Deadline deadline = new Deadline("ST1131 /by 2025-02-09");
        assertEquals("ST1131 (by: 2025-02-09T23:59)", deadline.getDescription());
    }

    @Test
    public void constructor_emptyDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new Deadline(" /by 2025-02-09"));
    }

    @Test
    public void constructor_emptyTime_throwsEmptyTimeException() {
        assertThrows(EmptyTimeException.class, () -> new Deadline("ST1131 /by "));
    }

    @Test
    public void constructor_invalidDeadline_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> new Deadline("ST1131 by 2025-02-09"));
    }

    @Test
    public void getRawDescriptionUtility_datetimeDeadline_ISO8601Test() {
        String description = "CS2103T /by 2025-02-07T16:00";
        assertEquals(description, Deadline.getRawDescription(new Deadline(description).getDescription()));
    }
}
