package steadylah.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import steadylah.exception.EmptyDescriptionException;
import steadylah.exception.EmptyTimeException;
import steadylah.exception.InvalidCommandException;

public class DeadlineTest {
    @Test
    public void getDescriptionDatetimeDeadlineIsoTest() {
        Deadline deadline = new Deadline("CS2103T /by 2025-02-07T16:00");
        assertEquals("CS2103T (by: 2025-02-07T16:00)", deadline.getDescription());
    }

    @Test
    public void getDescriptionDateOnlyDeadlineIsoMidnightTest() {
        Deadline deadline = new Deadline("ST1131 /by 2025-02-09");
        assertEquals("ST1131 (by: 2025-02-09T23:59)", deadline.getDescription());
    }

    @Test
    public void constructorEmptyDescriptionThrowsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new Deadline(" /by 2025-02-09"));
    }

    @Test
    public void constructorEmptyTimeThrowsEmptyTimeException() {
        assertThrows(EmptyTimeException.class, () -> new Deadline("ST1131 /by "));
    }

    @Test
    public void constructorInvalidDeadlineThrowsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> new Deadline("ST1131 by 2025-02-09"));
    }

    @Test
    public void getRawDescriptionUtilityDatetimeDeadlineIsoTest() {
        String description = "CS2103T /by 2025-02-07T16:00";
        assertEquals(description, Deadline.getRawDescription(new Deadline(description).getDescription()));
    }
}
