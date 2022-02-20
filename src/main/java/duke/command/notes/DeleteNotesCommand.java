package duke.command.notes;

import java.io.IOException;

import duke.command.Command;
import duke.others.Messages;
import duke.others.Utility;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteNotesCommand extends Command {
    protected String notes;
    protected int index;

    /**
     * Delete an existing note
     *
     * @param index of the task in the task list
     */
    public DeleteNotesCommand(int index) {
        this.index = index;
    }
    /**
     * Delete an existing note and updating data afterwards
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws IOException if there are errors appending the date to the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        task.deleteNotes();
        String newData = index + ";" + Utility.constructInput(task);
        storage.updateLine(index, newData);
        return Messages.NOTES_DELETE;
    }
}
