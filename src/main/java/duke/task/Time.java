package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time extends Task {
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    Time(String description, String timeString) {
        super(description);
        time = LocalDateTime.parse(timeString, formatter);
    }

    /**
     * To convert the time for saving.
     *
     * @return Time as a string.
     */
    String convertSaveTimeString() {
        return formatter.format(time);
    }

    /**
     * Send a converted time string to display to the user.
     *
     * @return A formatted timing.
     */
    String convertTimeString() {
        return DateTimeFormatter.ofPattern("MMM d yyyy HHmm").format(time);
    }

    /**
     * Return whether this task is on the given date.
     *
     * @param date Date which the task is checked to be on.
     * @return If the date match the given date, return the task.
     */
    public boolean isOn(LocalDate date) {
        return time.toLocalDate().isEqual(date);
    }

}
