package duke.others;

import java.time.LocalDate;

import duke.task.Task;

/**
 * Other helpful methods used in other classes.
 */
public class Utility {
    /**
     * Checks if the string contains only numeric characters.
     *
     * @param s input string.
     * @return true if all characters are numeric.
     */
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Concatenate the task attributes into a single string. Attributes are delimited by ";".
     *
     * @param task task object
     * @return a concatenated string of the task attributes
     */
    public static String constructInput(Task task) {
        String taskType = task.getType();
        boolean isDone = task.getIsDone();
        String desc = task.getDesc();
        LocalDate date = task.getDate();
        int doAfter = task.getDoAfter();
        int doBefore = task.getDoBefore();
        String notes = task.getNotes();
        String input;

        if (notes == null) {
            notes = "";
        }

        if (taskType.equals("D") || taskType.equals("E")) {
            input = taskType + ";" + isDone + ";" + desc + ";" + date
                    + ";" + doAfter + ";" + doBefore + ";" + notes;
        } else {
            input = taskType + ";" + task.getIsDone() + ";" + desc + ";" + ";" + doAfter
                    + ";" + doBefore + ";" + notes;
        }
        return input;
    }
}
