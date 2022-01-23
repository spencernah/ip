package duke.task;

import duke.DukeException;
import duke.StorageTemp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();
    private static final StorageTemp storageTemp = new StorageTemp();

    @Test
    void getCommand() throws IOException, DukeException {
        String toDoString = new ToDo("return book").toString();
        List<String> line = ToDo.getCommand(taskListTemp, storageTemp).run(
                new String[]{"todo", "return book"});
        assertEquals(List.of("Got it. I've added this task: " + System.lineSeparator()
                + "     " + toDoString + System.lineSeparator()
                + "   Now you have " + taskListTemp.size() + " tasks in the list."), line);
        assertEquals(toDoString, taskListTemp.doTask.toString());
        assertEquals(toDoString, storageTemp.stored.get(0));
    }

    @Test
    void getList() {
        List<String> list = new ToDo("return book").getList();
        assertEquals(List.of("T", "0", "return book"), list);
    }

    @Test
    void testToString() {
        String output = new ToDo(" return book").toString();
        String crossSymbol = " ";
        assertEquals("[T][" + crossSymbol + "] return book", output);
    }

    @AfterEach
    void clearTask() {
        taskListTemp.doTask = null;
        storageTemp.stored = null;
    }
}