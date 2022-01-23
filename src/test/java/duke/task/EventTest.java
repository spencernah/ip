package duke.task;

import duke.DukeException;
import duke.StorageTemp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();
    private static final StorageTemp storageTemp = new StorageTemp();

    @Test
    void getCommand() throws IOException, DukeException {
        String eventString = new Event("project meeting", "5/12/2022 1800").toString();
        List<String> line = Event.getCommand(taskListTemp, storageTemp).run(
                new String[]{"event", "project meeting", "/at", "5/12/2022 1800"});
        assertEquals(List.of("Got it. I've added this task: " + System.lineSeparator()
                + "     " + eventString + System.lineSeparator()
                + "   Now you have " + taskListTemp.size() + " tasks in the list."), line);
        assertEquals(eventString, taskListTemp.doTask.toString());
        assertEquals(eventString, storageTemp.stored.get(0));
    }

    @Test
    void getList() {
        List<String> list = new Event("project meeting", "5/12/2022 1800").getList();
        assertEquals(List.of("E", "0", "project meeting", "5/12/2022 1800"), list);
    }

    @Test
    void testToString() {
        String output = new Event(" project meeting", "5/12/2022 1800").toString();
        String crossSymbol = " ";
        assertEquals("[E][" + crossSymbol + "] project meeting (at: Dec 5 2022 1800)", output);
    }

    @AfterEach
    void clearTask() {
        taskListTemp.doTask = null;
        storageTemp.stored = null;
    }
}