package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeExceptionTest {
    @Test
    void testDukeException() {
        DukeException exception = new DukeException("Test");
        assertEquals("Test", exception.getMessage());
    }
}