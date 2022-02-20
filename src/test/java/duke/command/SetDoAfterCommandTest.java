package duke.command;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class SetDoAfterCommandTest {
    private String filePath = "C:\\Users\\User\\Documents\\ip\\data\\data_test.txt";
    private Ui ui = new Ui();
    private Storage storage = new Storage(filePath);
    private TaskList tasks;


    @BeforeEach
    public void setup() throws IOException {
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        } catch (IOException e) {
            throw new IOException("Can't read file");
        }
    }

    @Test
    public void executeValidCommand() throws IOException, DukeException {
        Command c = new SetDoAfterCommand(1, 3);
        c.execute(tasks, ui, storage);
        Assertions.assertEquals(tasks.get(1).getDoAfter(), 3);
    }

    @Test
    public void setInvalidParentChildIndex() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(3, 2);
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void setOutOfRangePositiveIndex() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(10, 3);
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void setOutOfRangeNegativeIndex() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(3, -1);
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void setSameIndexForParentChild() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(1, 1);
            c.execute(tasks, ui, storage);
        });
    }
}

