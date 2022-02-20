package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.others.DateFormat;
import duke.others.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     * @param filePath argument is the directory where the data for the existing task list (if any) is stored.
     *                 The argument is hardcoded at the moment.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.print("No existing To Do List. Generating a new one");
            tasks = new TaskList();
        } catch (IOException e) {
            ui.print("Unable to read specific lines in data.txt file");
        } catch (DateTimeParseException e) {
            ui.print("Dates in the data.txt file is incompatible. Duke reads date in " + DateFormat.STANDARD + " only");
        }
    }

    public String getResponse(String input) {
        Command c;

        try {
            c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Line not found";
        } catch (DateTimeParseException e) {
            return "Please enter the date in this format: " + DateFormat.STANDARD;
        }
    }
}
