package ui;

import java.util.Scanner;

public class Ui {
    protected Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void newline(){
        System.out.println("_____________________________________________________________");
    }
    public void welcome(){
        newline();
        System.out.println("Hellu, I ish Dook!");
        newline();
    }

    public String readCommand(){
        return in.nextLine();
    }

    public void echo(String input) {
        System.out.println(input);
        newline();
    }
    public void bye(){
        System.out.println("Buai :(");
        newline();
        in.close();
    }
}

