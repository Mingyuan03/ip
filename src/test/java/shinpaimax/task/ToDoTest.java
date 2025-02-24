package shinpaimax.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shinpaimax.exception.EmptyDescriptionException;

public class ToDoTest {
    @Test
    public void constructor_emptyDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new ToDo(" \t\n "));
    }
}
