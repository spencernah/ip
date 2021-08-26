import ui.Ui;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */

        Ui ui = new Ui();
        ui.welcome();
        while (!isExit) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                isExit = true;
                ui.bye();
            }
            else {
                ui.echo(command);
            }
        }


    }
}
