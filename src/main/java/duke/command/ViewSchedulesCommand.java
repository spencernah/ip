package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewSchedulesCommand implements Command {
    private TaskList tasks;

    public ViewSchedulesCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Return the message the user wants to see.
     *
     * @param fullComand Array of command from the user input.
     * @return Message to show the user.
     */
    @Override
    public List<String> run(String[] fullComand) {
        LocalDate dateFormat;
        String dateString = String.join(" ", Arrays.copyOfRange(fullComand, 1, fullComand.length));
        try {
            dateFormat = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return List.of("Please enter the date in the format d/M/yyyy e.g. 06/02/2022");
        }

        List<String> message = new ArrayList<>();
        message.add("Here is the schedule for " + dateString + ":");

        int countNumber = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Time && ((Time) task).isOn(dateFormat)) {
                countNumber++;
                message.add(countNumber + "." + task);
            }
        }
        return message;
    }
}
