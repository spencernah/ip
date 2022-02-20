package duke.command;

import java.io.IOException;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable duke.command class to be inherited
 */
public class Command {
    /** Represents the status of the application. If True, application will be terminated */
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return null;
    }


}
