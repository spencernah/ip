package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.others.DateFormat;

/**
 * A type of task where task is tied to a due date
 */
public class Deadline extends Task {
    /** Represents the due date of the task */
    protected LocalDate date;

    /**
     * A type of task that has a due date
     * @param desc is the name of task
     * @param date is the due date of task
     */
    public Deadline(String desc, LocalDate date, String notes) {
        super(desc, "D", notes);
        this.date = date;
    }

    /** @return the date of the event. Format = "yyyy-mm-dd" */
    public LocalDate getDate() {
        return this.date;
    }

    /** @return the date of the event. Format = "dd mm yyyy" */
    public String getFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern(DateFormat.EVENT_AND_DEADLINE));
    }

    /** @return the task type, task status (as a icon), task description and task date (in "dd mm yyyy" format). */
    public String getTypeStatusDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc + " (by: + getFormattedDate() + )";
    }
}
