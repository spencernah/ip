package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Display the status icon of this task (" " or "x" symbols) to the user.
     *
     * @return status icon.
     */
    public String getStatusIcon(){
        return (isDone ? "x" : " ");
    }

    /**
     *Return a list of strings to user.
     *
     * @return this string task.
     */
    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }
}
