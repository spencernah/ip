package duke.command.list;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Display list of tasks with notes that matches keyword(s).
 */
public class FilterByNotesCommand extends Command {
    protected String keyword;

    /**
     * @param input for note's keywords.
     */
    public FilterByNotesCommand(String input) {
        this.keyword = input;
    }

    /**
     * Find tasks with notes that matches the keyword(s)
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if task list is empty or there are no tasks with the matching keywords
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String input = getListByNotes(tasks);
        if (tasks.isEmpty() || input.length() == 0) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        input = Messages.LIST_HEADER + input;
        return input;
    }

    private String getListByNotes (TaskList tasks) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getNotes().matches(".*" + this.keyword + ".*")) {
                input = input.concat("\t" + (i + 1) + ". " + tasks.get(i).getTypeStatusDescNotes() + "\n");
            }
        }
        return input;
    }
}
