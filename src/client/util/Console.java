package client.util;

import java.io.PrintWriter;
import java.util.Scanner;

public class Console {
    private Scanner input;
    private PrintWriter output;
    private static Console instance;

    private Console() {
        input = new Scanner(System.in);
        output = new PrintWriter(System.out);
    }

    public static Console getInstance(){
        if(Console.instance == null)
           Console.instance = new Console();

        return Console.instance;
    }

    public String readLine() {
        while (!input.hasNextLine());
        return input.nextLine();
    }

    public synchronized void writeLine(String line) {
        output.println(line);
        output.flush();
    }
}
