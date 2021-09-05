package duke.task;

public class Event extends Task{

    private String time;

    public Event(String description, String time){
        super(description);
        this.time = time;
    }

    /**
     * Return a list of strings to user.
     *
     * @return this string task.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
