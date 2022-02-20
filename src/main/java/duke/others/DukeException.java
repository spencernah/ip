package duke.others;

/**
 * Duke-specific error handler.
 */
public class DukeException extends Exception {
    /**
     * Exception handler for Duke
     * @param err exception message
     */
    public DukeException(String err) {

        super(err);
    }
}
