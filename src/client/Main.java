package client;

import client.command.CommandParser;
import client.command.CommandReader;
import client.command.CommandSender;
import client.message.MessagePrinter;
import client.message.MessageReceiver;
import client.util.Console;
import client.util.SocketConnection;

import java.net.Socket;

public class Main {
    public static void main(String args[]) {
        Console console = Console.getInstance();
        String ip;
        int port;

        console.writeLine("Informe o endereço IP do servidor: ");
        ip = console.readLine();
        console.writeLine("Informe a porta: ");
        port = Integer.parseInt(console.readLine());

        try {
            SocketConnection connection = new SocketConnection(new Socket(ip, port));
            MessagePrinter messagePrinter = new MessagePrinter(console);
            MessageReceiver messageReceiver = new MessageReceiver(connection, messagePrinter);
            CommandParser commandParser = new CommandParser();
            CommandSender commandSender = new CommandSender(connection);
            CommandReader commandReader = new CommandReader(commandParser, commandSender, messagePrinter, console);

            messagePrinter.printHelpMessage();
            (new Thread(messageReceiver)).start();
            commandReader.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
