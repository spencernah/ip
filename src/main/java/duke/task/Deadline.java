package duke.task;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Return a list of strings to user.
     *
     * @return this string task.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
