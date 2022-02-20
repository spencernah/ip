package duke.task;

/** Default type of task */
public class ToDo extends Task {
    public ToDo(String desc, String notes) {
        super(desc, "T", notes);
    }
}
