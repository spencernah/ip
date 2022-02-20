package duke.command;

import duke.others.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * When user input/duke.command is not taken into account in the Parser.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {}

    /**
     * Executes the duke.command and prints a response.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Messages.MSG_UNKNOWN;
    }
}