package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Terminates the application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Executes the duke.command.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
        this.isExit = true;
        return null;
    }
}
