package duke.command;

import duke.DukeException;

import java.io.IOException;
import java.util.List;

public interface Command {

    /**
     * Returns the message to user after the command is run.
     *
     * @param fullCommand Array of command from the user input.
     * @return Message to the user.
     */
    List<String> run(String[] fullCommand) throws DukeException, IOException;

    /**
     * Return false when execute this command.
     *
     * @return False by default, the program should not exit when run this command.
     */
    default boolean isExit() {
        return false;
    }
}
