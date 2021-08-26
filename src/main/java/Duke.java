import ui.Ui;
import java.util.*;

public class Duke {
    protected static List<String> tasks = new ArrayList<String>();

    public static void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
    }

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

            switch(command) {
                case "bye":
                    isExit = true;
                    ui.bye();
                    break;
                case "hello":
                    ui.echo(command);
                    break;
                case "list":
                    printList();
                    ui.newline();
                    /*
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i+1 + ". " + tasks.get(i));
                    }
                    */
                    break;
                default:
                    ui.echo("added: " + command);
                    tasks.add(command);
            }

        }
    }
}

