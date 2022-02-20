package duke.command.list;

import java.time.LocalDate;
import java.time.Period;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Display list of upcoming tasks.
 */
public class FilterByUpcomingCommand extends Command {
    public FilterByUpcomingCommand() {
    }

    /**
     * Display a list of deadlines and events that are due today, tomorrow and overdue.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if there are no tasks in the task list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String input = getUpcoming(tasks);
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        return input;
    }

    private String getUpcoming(TaskList tasks) {
        LocalDate today = LocalDate.now();
        String dueToday = "";
        String dueTmr = "";
        String overdue = "";
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.getType().equals("T") || task.getIsDone()) {
                continue;
            }
            int dateDiff = Period.between(today, task.getDate()).getDays();
            if (dateDiff == 0) {
                dueToday = dueToday.concat((i + 1) + ". " + task.getTypeStatusDescNotes() + "\n");
            } else if (dateDiff == 1) {
                dueTmr = dueTmr.concat("\t" + (i + 1) + ". " + task.getTypeStatusDescNotes() + "\n");
            } else if (dateDiff < 0 && task.getType().equals("D")) {
                overdue = overdue.concat("\t" + (i + 1) + ". " + task.getTypeStatusDescNotes() + "\n");
            }
        }
        String input = "";
        if (dueToday.length() > 0) {
            input += "Today's deadline and events:\n" + dueToday;
        }
        if (dueTmr.length() > 0) {
            input += "\nTomorrow's deadline and events:\n" + dueTmr;
        }
        if (overdue.length() > 0) {
            input += "\nOverdue deadlines:\n" + overdue;
        }
        if (input.length() == 0) {
            input = "YAY!! Nothing too urgent at the moment!";
        }
        return input;
    }
}
