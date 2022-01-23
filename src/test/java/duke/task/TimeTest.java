package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTest {

    @Test
    void convertSaveTimeString() {
        String save = new Time("borrow book", "24/1/2022 1400").convertSaveTimeString();
        assertEquals("24/1/2022 1400", save);
    }

    @Test
    void convertTimeString() {
        String save = new Time("borrow book", "24/1/2022 1400").convertTimeString();
        assertEquals("Jan 24 2022 1400", save);
    }
}