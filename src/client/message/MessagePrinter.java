package client.message;

import client.util.Console;
import client.util.ConsoleColors;

public class MessagePrinter {
    private Console console;

    public MessagePrinter(Console console) {
        this.console = console;
    }

    public void printMessage(String sender, String content) {
        String senderLabel;

        senderLabel = ConsoleColors.GREEN_BOLD + sender + ": " + ConsoleColors.RESET;

        this.console.writeLine(senderLabel + content);
    }

    public void printErrorMessage(String content) {
        this.console.writeLine(ConsoleColors.RED_BACKGROUND + "Erro: " + content + ConsoleColors.RESET);
    }

    public void printHelpMessage() {
        synchronized (this.console) {
            this.console.writeLine("Comandos disponíveis: ");
            this.console.writeLine("/enter nickname - Entra na sala com o nickname informado");
            this.console.writeLine("/leave - Sai da sala");
            this.console.writeLine("/help - Exibe esta lista");
            this.console.writeLine("Para começar a enviar mensagens para a sala, envie o comando /enter com o nickname desejado");
        }
    }
}
