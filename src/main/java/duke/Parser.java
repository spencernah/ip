package duke;

import java.util.HashMap;
import java.util.Map;

import duke.command.Command;

public class Parser {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Capture a command to be returned later.
     *
     * @param name    The key word that trigger the command.
     * @param command For mapping the name and to be returned later.
     */
    public void capture(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * To check and return an appropriate command.
     *
     * @param fullCommand The input from user.
     * @return Command to use for the next processing.
     */
    public Command parse(String[] fullCommand) throws DukeException {
        Command command = commands.get(fullCommand[0].toLowerCase());
        if (command == null) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
