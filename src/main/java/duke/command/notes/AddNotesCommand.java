package duke.command.notes;

import java.io.IOException;

import duke.command.Command;
import duke.others.Messages;
import duke.others.Utility;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddNotesCommand extends Command {
    protected String notes;
    protected int index;

    /**
     * Update notes of a task
     *
     * @param index of the task in the task list
     * @param notes to be associated with the task
     */
    public AddNotesCommand(int index, String notes) {
        this.notes = notes;
        this.index = index;
    }
    /**
     * Append user's input to an existing note
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws IOException if there are errors appending the date to the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        if (!task.hasNotes()) {
            new UpdateNotesCommand(index, notes);
        }
        notes = task.getNotes().trim() + " " + notes;
        task.setNotes(notes);
        String newData = index + ";" + Utility.constructInput(task);
        storage.updateLine(index, newData);
        return Messages.NOTES_ADD + "\n\t" + notes;
    }
}
