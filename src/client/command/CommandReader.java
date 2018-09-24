package client.command;

import client.message.MessagePrinter;
import client.util.Console;

public class CommandReader implements Runnable {
    private CommandParser commandParser;
    private CommandSender commandSender;
    private MessagePrinter messagePrinter;
    private Console console;

    public CommandReader(CommandParser commandParser, CommandSender commandSender, MessagePrinter messagePrinter, Console console) {
        this.commandParser = commandParser;
        this.commandSender = commandSender;
        this.messagePrinter = messagePrinter;
        this.console = console;
    }

    @Override
    public void run() {
        while(true) {
            String command;
            Command parsed;

            command = this.console.readLine();
            parsed = this.commandParser.parse(command);

            switch (parsed.getType()){
                case "message": {
                    this.commandSender.sendMessageCommand(parsed.getParameter());
                    break;
                }
                case "enter": {
                    this.commandSender.sendEnterCommand(parsed.getParameter());
                    break;
                }
                case "leave": {
                    this.commandSender.sendLeaveCommand();
                    System.exit(0);
                    break;
                }
                case  "help": {
                    this.messagePrinter.printHelpMessage();
                    break;
                }
                default: {
                    this.messagePrinter.printErrorMessage("Comando inválido");
                }
            }

        }
    }
}
