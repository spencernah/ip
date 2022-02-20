package duke.command.list;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Display the list of tasks
 */
public class ListAllCommand extends Command {
    public ListAllCommand() {
    }

    /**
     * Executes the duke.command and display all tasks in the task list.
     *
     * @param tasks task list
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if task list is empty
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        String input = Messages.LIST_HEADER;
        input += getAll(tasks);
        return input;
    }

    private String getAll(TaskList list) {
        String input = "";
        for (int i = 0; i < list.size(); ++i) {
            input = input.concat((i + 1) + ". " + list.get(i).getTypeStatusDescNotes() + "\n");
        }
        return input;
    }
}
