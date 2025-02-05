package steadylah.task;

import steadylah.exception.EmptyDescriptionException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToDoTest {
    @Test
    public void constructor_emptyDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new ToDo(" \t\n "));
    }
}
