import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printWithLine(List.of("Hello from\n" + logo));
        printWithLine(List.of("Hello! I'm Duke", "What can I do for you?"));

        String line;
        Scanner in = new Scanner(System.in);
        String [] item = new String[100];
        int index = 0;

        do{
            line = in.nextLine();
            if (line.equals("list")){
                printWithLine(List.of());
                int k = 1;
                for (int i = 0; i < index; i++){
                    System.out.println("     " + k + ". " + item[i]);
                    k++;
                }
                printWithLine(List.of());
            }else if (!line.equals("bye")){
                item[index] = line;
                index++;
                printWithLine(List.of());
                printWithLine(List.of("added: " + line));
            }
        }while(!line.equals("bye"));

        printWithLine(List.of());
        printWithLine(List.of("Bye. Hope to see you again soon!"));
    }

    private static void printWithLine(List<String> messages){
        for (String message : messages){
            System.out.println("   " + message);
        }
        System.out.println("   _____________________________________");
    }
}
