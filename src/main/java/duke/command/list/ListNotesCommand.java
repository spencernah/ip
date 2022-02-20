package duke.command.list;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Display list of pending tasks.
 */
public class ListNotesCommand extends Command {

    public ListNotesCommand() {
    }

    /**
     * Display a list of tasks that has notes.
     *
     * @param tasks task list
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if task list is empty or there are no tasks with notes.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String input = getListWithNotes(tasks);
        if (tasks.isEmpty() || input.length() == 0) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        input = Messages.LIST_HEADER + input;
        return input;
    }

    private String getListWithNotes(TaskList tasks) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).hasNotes()) {
                input = input.concat((i + 1) + ". " + tasks.get(i).getTypeStatusDescNotes() + "\n");
            }
        }
        return input;
    }
}
