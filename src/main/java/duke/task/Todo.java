package duke.task;

public class Todo extends Task{

    public Todo (String description){
        super(description);
    }

    /**
     * Return a list of strings to user.
     *
     * @return this string task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
