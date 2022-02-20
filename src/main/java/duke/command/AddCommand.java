package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.others.Utility;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    protected String type;
    protected String desc;
    protected LocalDate date;

    /**
     * Add a Deadline or Event task.
     *
     * @param type task type.
     * @param desc task description.
     * @param date date of event or due date of deadline.
     */
    public AddCommand(String type, String desc, LocalDate date) {
        this.type = type;
        this.desc = desc;
        this.date = date;
    }

    /**
     * Add a ToDo task.
     *
     * @param type task type.
     * @param desc task description.
     */
    public AddCommand(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws IOException if there are errors appending the date to the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        switch(this.type) {
        case("todo"):
            tasks.add(new ToDo(this.desc));
            break;
        case("event"):
            tasks.add(new Event(this.desc, this.date));
            break;
        case("deadline"):
            tasks.add(new Deadline(this.desc, this.date));
            break;
        default:
        }
        int index = tasks.size() - 1;
        Task task = tasks.get(index);
        storage.append(index + ";" + Utility.constructInput(task));
        return "New task added: \n\t" + task.getStatusIconAndDesc() + "\n" + (index + 1) + " tasks in your list";
    }
}
