package duke.task;

import duke.DukeException;
import duke.StorageTemp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();
    private static final StorageTemp storageTemp = new StorageTemp();

    @Test
    void getCommand() throws IOException, DukeException {
        String deadlineString = new Deadline("return book", "5/12/2022 1800").toString();
        List<String> line = Deadline.getCommand(taskListTemp, storageTemp).run(
                new String[]{"deadline", "return book", "/by", "5/12/2022 1800"});
        assertEquals(List.of("Got it. I've added this task: " + System.lineSeparator()
                + "     " + deadlineString + System.lineSeparator()
                + "   Now you have " + taskListTemp.size() + " tasks in the list."), line);
        assertEquals(deadlineString, taskListTemp.doTask.toString());
        assertEquals(deadlineString, storageTemp.stored.get(0));
    }

    @Test
    void getList() {
        List<String> list = new Deadline("return book", "5/12/2022 1800").getList();
        assertEquals(List.of("D", "0", "return book", "5/12/2022 1800"), list);
    }

    @Test
    void testToString() {
        String output = new Deadline(" return book", "5/12/2022 1800").toString();
        String crossSymbol = " ";
        assertEquals("[D][" + crossSymbol + "] return book (by: Dec 5 2022 1800)", output);
    }

    @AfterEach
    void clearTask() {
        taskListTemp.doTask = null;
        storageTemp.stored = null;
    }
}