package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.list.FilterByDateCommand;
import duke.command.list.FilterByDescCommand;
import duke.command.list.FilterByNotesCommand;
import duke.command.list.FilterByStatusCommand;
import duke.command.list.FilterByUpcomingCommand;
import duke.command.list.ListAllCommand;
import duke.command.notes.AddNotesCommand;
import duke.command.notes.DeleteNotesCommand;
import duke.command.notes.UpdateNotesCommand;
import duke.command.task.AddCommand;
import duke.command.task.DeleteCommand;
import duke.command.task.DoneCommand;
import duke.command.task.SetDoAfterCommand;
import duke.command.util.ExitCommand;
import duke.command.util.UnknownCommand;
import duke.others.DukeException;
import duke.others.ErrorMessages;
import duke.others.Keyword;
import duke.others.Utility;

public class Parser {
    /**
     * Parses user input.
     */
    public Parser() {

    }

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string.
     * @return the command based on the user input.
     */
    public static Command parse(String input) throws DukeException, DateTimeParseException {
        checkForDelimiter(input);
        String keyword = getKeyword(input.trim());
        String param = "";
        String desc;
        String dateStr;
        String notes = "";
        String taskIndex;
        LocalDate date = null;
        boolean hasParam = true;
        boolean isParamANumber = true;

        if (input.length() != keyword.length()) {
            param = removeKeyword(input, keyword).trim();
            hasParam = param.length() > 0;
            isParamANumber = Utility.isNumber(param);
        }
        if (hasDate(input)) {
            dateStr = getDateAsStr(input);
            date = LocalDate.parse(dateStr);
        }
        if (hasNotes(input)) {
            notes = getNotes(input);
        }

        switch (keyword) {
        case Keyword.EXIT_1:
        case Keyword.EXIT_2:
            return new ExitCommand();
        case Keyword.LIST_ALL:
            return new ListAllCommand();
        case Keyword.LIST_BY_STATUS_PENDING_1:
        case Keyword.LIST_BY_STATUS_PENDING_2:
            return new FilterByStatusCommand("pending");
        case Keyword.LIST_BY_STATUS_COMPLETED_1:
        case Keyword.LIST_BY_STATUS_COMPLETED_2:
            return new FilterByStatusCommand("completed");
        case Keyword.LIST_BY_DATE:
            return new FilterByDateCommand(date);
        case Keyword.FIND:
            if (hasParam) {
                return new FilterByDescCommand(param);
            } else {
                throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_FIND);
            }
        case Keyword.LIST_BY_NOTES:
            if (hasParam) {
                return new FilterByNotesCommand(param);
            } else if (notes.length() > 0) {
                return new FilterByNotesCommand(notes);
            } else {
                throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_LIST_NOTES);
            }
        case Keyword.REMINDER:
            return new FilterByUpcomingCommand();
        case Keyword.DONE:
            if (isParamANumber) {
                return new DoneCommand(Integer.parseInt(param) - 1);
            } else {
                throw new DukeException(ErrorMessages.NOT_A_INT);
            }
        case Keyword.DELETE:
            if (isParamANumber) {
                return new DeleteCommand(Integer.parseInt(param));
            } else {
                throw new DukeException(ErrorMessages.NOT_A_INT);
            }
        case Keyword.DEADLINE:
        case Keyword.EVENT:
            desc = getDesc(param);
            return new AddCommand(keyword, desc, date, notes);
        case Keyword.TODO:
            desc = getDesc(param);
            return new AddCommand(keyword, desc, notes);
        case Keyword.DOAFTER_1:
        case Keyword.DOAFTER_2:
            String taskIndex1 = param.substring(0, param.indexOf(" "));
            checkDoAfterSyntax(param);
            String taskIndex2 = param.substring(param.lastIndexOf("/") + 1).trim();
            if (!hasParam) {
                throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_DOAFTER_MISSING_PARAM);
            } else if (!Utility.isNumber(taskIndex1) || !Utility.isNumber(taskIndex2)) {
                throw new DukeException(ErrorMessages.NOT_A_INT);
            }
            return new SetDoAfterCommand(Integer.parseInt(taskIndex1) - 1,
                    Integer.parseInt(taskIndex2) - 1);
        case Keyword.NOTES_ADD_1:
        case Keyword.NOTES_ADD_2:
            taskIndex = param.substring(0, param.indexOf(" "));
            if (!Utility.isNumber(taskIndex)) {
                throw new DukeException(ErrorMessages.NOT_A_INT);
            }
            if (isEmpty(notes)) {
                notes = param.substring(param.indexOf(" ") + 1);
            }
            if (isEmpty(notes)) {
                throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_NOTES_MISSING_TEXT);
            }
            return new AddNotesCommand(Integer.parseInt(taskIndex) - 1, notes);
        case Keyword.NOTES_UPDATE_1:
        case Keyword.NOTES_UPDATE_2:
            taskIndex = param.substring(0, param.indexOf(" "));
            if (!Utility.isNumber(taskIndex)) {
                throw new DukeException(ErrorMessages.NOT_A_INT);
            }
            if (isEmpty(notes)) {
                notes = param.substring(param.indexOf(" ") + 1);
            }
            if (isEmpty(notes)) {
                throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_NOTES_MISSING_TEXT);
            }
            return new UpdateNotesCommand(Integer.parseInt(taskIndex) - 1, notes);
        case Keyword.NOTES_DELETE_1:
        case Keyword.NOTES_DELETE_2:
            taskIndex = param;
            if (!Utility.isNumber(taskIndex)) {
                throw new DukeException(ErrorMessages.NOT_A_INT);
            }
            return new DeleteNotesCommand(Integer.parseInt(taskIndex) - 1);
        default:
            return new UnknownCommand();
        }
    }

    private static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }

    /**
     * Get keyword of the command based on the user input.
     *
     * @param input full user input string.
     * @return keyword of the duke.command.
     */
    private static String getKeyword(String input) {
        if (input.matches(Keyword.DONE + ".*")) {
            return Keyword.DONE;
        } else if (input.matches(Keyword.DELETE + ".*")) {
            return Keyword.DELETE;
        } else if (input.matches(Keyword.DEADLINE + ".*")) {
            return Keyword.DEADLINE;
        } else if (input.matches(Keyword.EVENT + ".*")) {
            return Keyword.EVENT;
        } else if (input.matches(Keyword.TODO + ".*")) {
            return Keyword.TODO;
        } else if (input.matches(Keyword.LIST_BY_DATE + ".*")) {
            return Keyword.LIST_BY_DATE;
        } else if (input.matches(Keyword.FIND + ".*")) {
            return Keyword.FIND;
        } else if (input.matches(Keyword.DOAFTER_1 + ".*")) {
            return Keyword.DOAFTER_1;
        } else if (input.matches(Keyword.DOAFTER_2 + ".*")) {
            return Keyword.DOAFTER_2;
        } else if (input.matches(Keyword.NOTES_ADD_1 + ".*")) {
            return Keyword.NOTES_ADD_1;
        } else if (input.matches(Keyword.NOTES_ADD_2 + ".*")) {
            return Keyword.NOTES_ADD_2;
        } else if (input.matches(Keyword.NOTES_UPDATE_1 + ".*")) {
            return Keyword.NOTES_UPDATE_1;
        } else if (input.matches(Keyword.NOTES_UPDATE_2 + ".*")) {
            return Keyword.NOTES_UPDATE_2;
        } else if (input.matches(Keyword.NOTES_DELETE_1 + ".*")) {
            return Keyword.NOTES_DELETE_1;
        } else if (input.matches(Keyword.NOTES_DELETE_2 + ".*")) {
            return Keyword.NOTES_DELETE_2;
        } else if (input.matches(Keyword.LIST_BY_NOTES + ".*")) {
            return Keyword.LIST_BY_NOTES;
        }
        return input;
    }

    /**
     * Remove keyword to get the command parameters (if applicable).
     *
     * @param input full user input string.
     * @param keyword duke.command keyword.
     * @return duke.command parameters.
     */
    private static String removeKeyword(String input, String keyword) {
        int endIndex = input.length();
        int startIndex = keyword.length() + 1;
        return input.substring(startIndex, endIndex).trim();
    }

    /**
     * Checks if user has input a date when entering a create Event/Deadline command.
     *
     * @param param duke.command parameter.
     * @throws DukeException if user did not lead the date with a "/" character, did not enter a date or enter an
     * invalid date format (accepted format = "yyyy-mm-dd" or "yyyy-m-d").
     */
    private static void checkDate(String param) throws DukeException {
        int delimiterIndex = param.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_NO_LEADING_DATE_DELIMITER);
        }
        String date = param.substring(delimiterIndex + 1);
        if (date.length() == 0) {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_NO_DATE_AFTER_DELIMITER);
        }
        if (date.lastIndexOf("-") < 0) {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_INCORRECT_DATE_FORMAT);
        }
    }

    /**
     * Condenses all the methods that needs to be performed to ensure that the date input is correct and return the
     * correct date format (if applicable).
     *
     * @param input full user input string.
     * @return date string in the correct format (yyyy-mm-dd).
     * @throws DukeException from checkDate() method.
     */
    private static String getDateAsStr(String input) throws DukeException {
        checkDate(input);
        input = input.substring(input.lastIndexOf("/") + 1).trim();
        input = changeDateFormat(input);
        return input;
    }

    /**
     * Change the date parameter into a format that can be parsed to LocalDate.
     *
     * @param date date parameter.
     * @return a date string in "yyyy-mm-dd" format.
     */
    private static String changeDateFormat (String date) {
        String day = date.substring(date.lastIndexOf("-") + 1);
        if (day.length() == 1) {
            day = "0" + day;
        }
        String month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"));
        if (month.length() == 1) {
            month = "0" + month;
        }
        String year = date.substring(0, 4);
        return year + "-" + month + "-" + day;
    }

    /**
     * Ensure that the users do not input any delimiter character
     *
     * @param input full user input string.
     * @throws DukeException if delimiter character is used in input
     */
    private static void checkForDelimiter(String input) throws DukeException {
        if (input.contains(";")) {
            throw new DukeException(ErrorMessages.INCORECT_SYNTAX_INVALID_CHAR);
        }
    }

    private static void checkDoAfterSyntax(String param) throws DukeException {
        int delimiterIndex = param.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_DOAFTER_MISSING_PARAM);
        }
        String date = param.substring(delimiterIndex + 1);
        if (date.length() == 0) {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_DOAFTER_NO_LEADING_DELIMITER);
        }
    }

    private static boolean hasDate(String input) {
        return input.contains("\\");
    }

    private static boolean hasNotes(String input) throws DukeException {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\"') {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else if (count == 1) {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_NOTES_MISSING_QUOTATION_MARK);
        }
        return false;
    }


    /**
     * Checks if user has input a task description when trying to create a task.
     *
     * @param input values entered by user.
     */
    private static boolean hasDesc(String input) throws DukeException {
        boolean isLenZero = input.length() == 0;
        boolean isDate = input.charAt(0) == '/';
        boolean isNotes = input.charAt(0) != '\"';

        return !(isLenZero && isDate && isNotes);
    }

    private static String getNotes(String input) throws DukeException {
        if (hasNotes(input)) {
            return input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\""));
        }
        return null;
    }

    private static String getDesc(String input) throws DukeException {
        String output = input;
        if (hasDate(input)) {
            output = input.substring(0, input.indexOf("\\"));
        } else if (hasNotes(input)) {
            output = input.substring(0, input.indexOf("\""));
        }
        if (hasDesc(output)) {
            return output;
        } else {
            throw new DukeException(ErrorMessages.INCORRECT_SYNTAX_TASK_NO_DESC);
        }
    }
}
