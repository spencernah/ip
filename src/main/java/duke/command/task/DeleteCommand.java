package duke.command.task;

import java.io.IOException;

import duke.command.Command;
import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * @param input index of the task in the task list.
     */
    public DeleteCommand(int input) {
        this.index = input - 1;
    }

    /**
     * Delete a specific task from the task list.
     * If deleted task has a parent or child task, update them to accordingly.
     *
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if the task list is empty or task index input is out of range.
     * @throws IOException if there are errors writing the data to the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        } else if (tasks.size() <= this.index) {
            throw new DukeException("Please enter a task number between 1 and " + tasks.size());
        } else {
            updateChildAndParent(tasks);
            tasks.remove(this.index);
            storage.save(tasks);
            return "Yessir! Task removed!!\n\t" + (tasks.pendingSize()) + " pending task(s) to go!";
        }
    }

    private void updateChildAndParent(TaskList tasks) {
        Task currentTask = tasks.get(this.index);
        if (!currentTask.isDoBeforeEmpty()) {
            Task parentTask = tasks.get(currentTask.getDoBefore());
            parentTask.setDoAfter(-1);
        }
        if (!currentTask.isDoAfterEmpty()) {
            Task childTask = tasks.get(currentTask.getDoAfter());
            childTask.setDoBefore(-1);
        }
    }
}
