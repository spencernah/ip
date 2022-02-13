package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ViewSchedulesCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class Duke {
    private Ui ui;
    private Parser parser;

    /**
     * Constructs Duke application.
     *
     * @param filePath The file path of the save file for tasks storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                ui.showLoadingError("☹ OOPS!!! Cannot load tasks. May overwrite old tasks, if continue");
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
        parser = new Parser();
        parser.capture("todo", ToDo.getCommand(tasks, storage));
        parser.capture("event", Event.getCommand(tasks, storage));
        parser.capture("deadline", Deadline.getCommand(tasks, storage));
        parser.capture("list", new ListCommand(tasks));
        parser.capture("done", new DoneCommand(tasks, storage));
        parser.capture("delete", new DeleteCommand(tasks, storage));
        parser.capture("bye", new ByeCommand());
        parser.capture("find", new FindCommand(tasks));
        parser.capture("viewschedules", new ViewSchedulesCommand(tasks));
    }

    static void wordChecker(String keyWord) throws DukeCheckLineException {
        String keyword = keyWord.toLowerCase();

        if (!keyword.equals("list") && !keyword.equals("bye")
                && !keyword.equals("todo") && !keyword.equals("done")
                && !keyword.equals("event") && !keyword.equals("deadline")
                && !keyword.equals("delete") && !keyword.equals("find")
                && !keyword.equals("viewschedules")) {
            throw new DukeCheckLineException();
        }
    }

    /**
     * Run Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit && ui.hasNextLine()) {
            String[] fullCommand = ui.commandReader().split(" ");
            ui.printWithLine(List.of());
            try {
                wordChecker(fullCommand[0]);
                Command c = parser.parse(fullCommand);
                ui.printCommand(c.run(fullCommand));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
                e.printStackTrace();
            } catch (DukeCheckLineException e) {
                ui.showError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    String getResponse(String input) {
        try {
            String[] fullCommand = input.split(" ");
            Command command = parser.parse(fullCommand);
            return String.join("\n", command.run(fullCommand));
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    boolean isExit(String input) {
        String[] fullCommand = input.split(" ");
        try {
            return parser.parse(fullCommand).isExit();
        } catch (DukeException e) {
            return false;
        }
    }
}