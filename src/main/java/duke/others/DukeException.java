package duke.others;

/**
 * Duke-specific error handler.
 */
public class DukeException extends Exception {
    public DukeException(String err) {
        super(err);
    }
}