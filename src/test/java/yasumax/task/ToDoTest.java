package yasumax.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yasumax.exception.EmptyDescriptionException;

public class ToDoTest {
    @Test
    public void constructor_emptyDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new ToDo(" \t\n "));
    }
}
