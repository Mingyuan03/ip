package steadylah.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import steadylah.exception.EmptyDescriptionException;

public class ToDoTest {
    @Test
    public void constructorEmptyDescriptionThrowsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> new ToDo(" \t\n "));
    }
}
