package duke.task;

public class FileTaskList extends Task{

    public FileTaskList (String description){
        super(description);
    }

    /**
     * Return a list of strings to user.
     *
     * @return this string task.
     */
    @Override
    public String toString(){
        return super.fileListToString();
    }
}
