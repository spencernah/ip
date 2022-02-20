package duke.others;

/**
 * Common messages used by methods in different classes.
 */
public class Messages {
    public static final String LIST_HEADER = "Yessir! Here is the list!\n";
    public static final String LIST_EMPTY = "No tasks in the requested list!";
    public static final String MSG_END = "Oyasumi~";
    public static final String MSG_GREETING = "Harrowwwwwww\nWut iz up?";
    public static final String MSG_UNKNOWN = "Sorry, I don't understand this :(";
    public static final String ERR_NOT_A_INT = "Please enter an integer";
    public static final String ERR_INCORRECT_SYNTAX_FIND = "Please enter a parameter after \"find\"";
    public static final String ERR_INCORRECT_SYNTAX_NO_LEADING_DATE_DELIMITER = "Please enter a date "
            + "and lead it with \"/\"";
    public static final String ERR_INCORRECT_SYNTAX_NO_DATE_AFTER_DELIMITER = "Please include a "
            + "date after the \"/\" :)";
    public static final String ERR_INCORRECT_SYNTAX_INCORRECT_DATE_FORMAT = "Duke reads date in " + DateFormat.STANDARD
            + ". Please separate your dates with \"-\" (eg. 2019-08-15)";
    public static final String ERR_INCORECT_SYNTAX_INVALID_CHAR = "Please refrain from using the character \";\"";
    public static final String ERR_INCORRECT_SYNTAX_DOAFTER_MISSING_PARAM = "Please enter a task number and "
            + "lead it with \"/\"\n(eg. doafter 3 /2)";
    public static final String ERR_INCORRECT_SYNTAX_DOAFTER_NO_LEADING_DELIMITER = "Please include a "
            + "task number after the \"/\" :)";
    public static final String ERR_INCORRECT_SYNTAX_NOTES_MISSING_QUOTATION_MARK = "Missing quotation mark (\"). "
            + "Please enclose notes within 2 quotation marks!";
    public static final String ERR_INCORRECT_SYNTAX_TASK_NO_DESC = "Task Description cannot be empty!!";
}
