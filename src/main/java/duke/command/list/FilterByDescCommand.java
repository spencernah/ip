package duke.command.list;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Display list of tasks based on user input task description.
 */
public class FilterByDescCommand extends Command {
    protected String keyword;

    /**
     * @param input user input task description.
     */
    public FilterByDescCommand(String input) {
        this.keyword = input;
    }

    /**
     * Find the corresponding task in the task list.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if task list is empty or there are no tasks with date equals to the user input date.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String input = getListByDesc(tasks);
        if (tasks.isEmpty() || input.length() == 0) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        input = Messages.LIST_HEADER + input;
        return input;
    }

    private String getListByDesc (TaskList tasks) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDesc().matches(".*" + this.keyword + ".*")) {
                input = input.concat("\t" + (i + 1) + ". " + tasks.get(i).getTypeStatusDescNotes() + "\n");
            }
        }
        return input;
    }
}
