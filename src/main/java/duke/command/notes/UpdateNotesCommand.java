package duke.command.notes;

import java.io.IOException;

import duke.command.Command;
import duke.others.Messages;
import duke.others.Utility;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateNotesCommand extends Command {
    protected String notes;
    protected int index;

    /**
     * Append user's input to an existing note

     *
     * @param index of the task in the task list
     * @param notes to be associated with the task
     */
    public UpdateNotesCommand(int index, String notes) {
        this.notes = notes;
        this.index = index;
    }
    /**
     * Replace the entire existing note with a new one
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws IOException if there are errors appending the date to the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        task.setNotes(notes);
        String newData = index + ";" + Utility.constructInput(task);
        storage.updateLine(index, newData);
        return Messages.NOTES_UPDATE + "\n\t" + notes;
    }
}
