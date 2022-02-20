package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.SetDoAfterCommand;
import duke.command.UnknownCommand;
import duke.command.ViewAllCommand;
import duke.command.ViewByDateCommand;
import duke.command.ViewByStatusCommand;
import duke.command.ViewByUpcomingCommand;
import duke.others.DateFormat;
import duke.others.DukeException;
import duke.others.Keyword;
import duke.others.Utility;

public class Parser {
    public static final String KEYWORD_EXIT_1 = Keyword.EXIT_1;
    public static final String KEYWORD_EXIT_2 = Keyword.EXIT_2;
    public static final String KEYWORD_DONE = Keyword.DONE;
    public static final String KEYWORD_DELETE = Keyword.DELETE;
    public static final String KEYWORD_TODO = Keyword.ADD_TODO;
    public static final String KEYWORD_DEADLINE = Keyword.ADD_DEADLINE;
    public static final String KEYWORD_EVENT = Keyword.ADD_EVENT;
    public static final String KEYWORD_LIST_DATE = Keyword.VIEW_BY_DATE;
    public static final String KEYWORD_LIST = Keyword.VIEW_ALL;
    public static final String KEYWORD_PENDING_1 = Keyword.VIEW_BY_STATUS_1;
    public static final String KEYWORD_PENDING_2 = Keyword.VIEW_BY_STATUS_2;
    public static final String KEYWORD_FIND = Keyword.FIND;
    public static final String KEYWORD_REMINDER = Keyword.REMINDER;
    public static final String KEYWORD_DO_AFTER_1 = Keyword.DO_AFTER_1;
    public static final String KEYWORD_DO_AFTER_2 = Keyword.DO_AFTER_2;
    public static final String ERR_NOT_A_INT = "Please enter an integer";

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
        String param;
        String desc;
        String dateStr;
        String notes = null;
        LocalDate date;

        switch (keyword) {
        case KEYWORD_EXIT_1:
        case KEYWORD_EXIT_2:
            return new ExitCommand();
        case KEYWORD_LIST:
            return new ViewAllCommand();
        case KEYWORD_PENDING_1:
        case KEYWORD_PENDING_2:
            return new ViewByStatusCommand();
        case KEYWORD_LIST_DATE:
            dateStr = getDateAsStr(input);
            date = LocalDate.parse(dateStr);
            return new ViewByDateCommand(date);
        case KEYWORD_FIND:
            param = removeKeyword(input, KEYWORD_FIND).trim();
            if (param.length() == 0) {
                throw new DukeException("Please enter a parameter after \"find\"");
            }
            return new FindCommand(param);
        case KEYWORD_REMINDER:
            return new ViewByUpcomingCommand();
        case KEYWORD_DONE:
            param = removeKeyword(input, KEYWORD_DONE);
            if (Utility.isNumber(param)) {
                return new DoneCommand(Integer.parseInt(param) - 1);
            } else {
                throw new DukeException(ERR_NOT_A_INT);
            }
        case KEYWORD_DELETE:
            param = removeKeyword(input, KEYWORD_DELETE);
            if (Utility.isNumber(param)) {
                return new DeleteCommand(Integer.parseInt(param));
            } else {
                throw new DukeException(ERR_NOT_A_INT);
            }
        case KEYWORD_DEADLINE:
        case KEYWORD_EVENT:
            param = removeKeyword(input, keyword);
            dateStr = getDateAsStr(input);
            date = LocalDate.parse(dateStr);
            desc = getDesc(param);
            notes = getNotes(param);
            return new AddCommand(keyword, desc, date, notes);
        case KEYWORD_TODO:
            param = removeKeyword(input, keyword);
            desc = getDesc(param);
            notes = getNotes(param);
            return new AddCommand(keyword, desc, notes);
        case(KEYWORD_DO_AFTER_1):
        case(KEYWORD_DO_AFTER_2):
            param = removeKeyword(input, keyword).trim();
            if (param.length() == 0) {
                throw new DukeException("Please enter a parameter after \"doafter\"");
            }
            String taskIndex1 = param.substring(0, 1);
            if (!Utility.isNumber(taskIndex1)) {
                throw new DukeException(ERR_NOT_A_INT);
            }
            checkTaskIndex(param);
            String taskIndex2 = param.substring(param.lastIndexOf("/") + 1).trim();
            if (!Utility.isNumber(taskIndex2)) {
                throw new DukeException(ERR_NOT_A_INT);
            }
            return new SetDoAfterCommand(Integer.parseInt(taskIndex1) - 1,
                    Integer.parseInt(taskIndex2) - 1);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Get keyword of the command based on the user input.
     *
     * @param input full user input string.
     * @return keyword of the duke.command.
     */
    private static String getKeyword(String input) {
        if (input.matches(KEYWORD_DONE + ".*")) {
            return KEYWORD_DONE;
        } else if (input.matches(KEYWORD_DELETE + ".*")) {
            return KEYWORD_DELETE;
        } else if (input.matches(KEYWORD_DEADLINE + ".*")) {
            return KEYWORD_DEADLINE;
        } else if (input.matches(KEYWORD_EVENT + ".*")) {
            return KEYWORD_EVENT;
        } else if (input.matches(KEYWORD_TODO + ".*")) {
            return KEYWORD_TODO;
        } else if (input.matches(KEYWORD_LIST_DATE + ".*")) {
            return KEYWORD_LIST_DATE;
        } else if (input.matches(KEYWORD_FIND + ".*")) {
            return KEYWORD_FIND;
        } else if (input.matches(KEYWORD_DO_AFTER_1 + ".*")) {
            return KEYWORD_DO_AFTER_1;
        } else if (input.matches(KEYWORD_DO_AFTER_2 + ".*")) {
            return KEYWORD_DO_AFTER_2;
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
        return input.replace(keyword, "").trim();
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
            throw new DukeException("Please enter a date and lead it with \"/\"");
        }
        String date = param.substring(delimiterIndex + 1);
        if (date.length() == 0) {
            throw new DukeException("Please include a date after the \"/\" :)");
        }
        if (date.lastIndexOf("-") < 0) {
            throw new DukeException("Duke reads date in " + DateFormat.STANDARD
                    + ". Please separate your dates with \"-\" (eg. 2019-08-15)");
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
            throw new DukeException("Please refrain from using the character \";\"");
        }
    }

    private static void checkTaskIndex(String param) throws DukeException {
        int delimiterIndex = param.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException("Please enter a task number and lead it with \"/\"\n(eg. doafter 3 /2)");
        }
        String date = param.substring(delimiterIndex + 1);
        if (date.length() == 0) {
            throw new DukeException("Please include a task number after the \"/\" :)");
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
            throw new DukeException("Missing quotation mark (\"). Please enclose notes within 2 quotation marks!");
        }
        return false;
    }


    /**
     * Checks if user has input a task description when trying to create a task.
     *
     * @param input values entered by user.
     */
    private static boolean hasDesc(String input) throws DukeException {
        return input.length() != 0 && input.charAt(0) != '/' && input.charAt(0) != '\"';
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
            throw new DukeException("Task Description cannot be empty!!");
        }
    }
}
