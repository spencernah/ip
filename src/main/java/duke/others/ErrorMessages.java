package duke.others;

public class ErrorMessages {
    public static final String NOT_A_INT = "Please enter an integer";
    public static final String INCORRECT_SYNTAX_FIND = "Please enter a parameter after \"find\"";
    public static final String INCORRECT_SYNTAX_NO_LEADING_DATE_DELIMITER = "Please enter a date "
            + "and lead it with \"/\"";
    public static final String INCORRECT_SYNTAX_NO_DATE_AFTER_DELIMITER = "Please include a "
            + "date after the \"/\" :)";
    public static final String INCORRECT_SYNTAX_INCORRECT_DATE_FORMAT = "Duke reads date in " + DateFormat.STANDARD
            + ". Please separate your dates with \"-\" (eg. 2019-08-15)";
    public static final String INCORECT_SYNTAX_INVALID_CHAR = "Please refrain from using the character \";\"";
    public static final String INCORRECT_SYNTAX_DOAFTER_MISSING_PARAM = "Please enter a task number and "
            + "lead it with \"/\"\n(eg. doafter 3 /2)";
    public static final String INCORRECT_SYNTAX_DOAFTER_NO_LEADING_DELIMITER = "Please include a "
            + "task number after the \"/\" :)";
    public static final String INCORRECT_SYNTAX_NOTES_MISSING_QUOTATION_MARK = "Missing quotation mark (\"). "
            + "Please enclose notes within 2 quotation marks!";
    public static final String INCORRECT_SYNTAX_TASK_NO_DESC = "Task Description cannot be empty!!";
    public static final String INCORRECT_SYNTAX_NOTES_MISSING_TEXT = "Please enter notes to be added!!";
    public static final String INCORRECT_SYNTAX_LIST_NOTES = "Please enter a parameter after \"list notes\"";
    public static final String MISSING_DATE = "Please input a date! (Reminder: Lead the date with \"/\")";
}
