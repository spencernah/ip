package duke.parser;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Keyword;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;




public class ParserTest {

    private Ui ui = new Ui();
    private Storage storage = new Storage("/Users/spencernah/code/duke/data/data_test.txt");
    private TaskList tasks;

    @BeforeEach
    public void setup() {
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseViewByDateCommandNoDate() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.VIEW_BY_DATE);
        });
    }

    @Test
    public void parseViewByDateCommandIncorrectDateFormat() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.VIEW_BY_DATE + "/20190805");
        });
    }

    @Test
    public void parseDoneCommandIncorrectIndex() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse("done abc");
        });
    }

    @Test
    public void parseDeleteCommandIncorrectIndex() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse("delete abc");
        });
    }

    @Test
    public void parseAddCommandDeadlineNoDesc() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_DEADLINE);
        });
    }

    @Test
    public void parseAddCommandDeadlineNoDate() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_DEADLINE + "test/");
        });
    }

    @Test
    public void parseAddCommandDeadlineIncorrectDateFormat() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_DEADLINE + "test/20190805");
        });
    }

    @Test
    public void parseAddCommandTodoNoDesc() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_TODO);
        });
    }

    @Test
    public void parseAddCommandDeadlineDateFormat() throws DukeException {
        String test = "";
        try {
            Command c = Parser.parse(Keyword.ADD_DEADLINE + "test/2019-8-5");
            c.execute(tasks, ui, storage);
            test = ((Deadline) tasks.get(tasks.size() - 1)).getFormattedDate();
        } catch (DukeException | IOException e) {
            throw new DukeException("Date format is incorrect");
        }
        Assertions.assertEquals(test, "05 Aug 2019");
    }
}
