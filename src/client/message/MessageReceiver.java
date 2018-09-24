package client.message;

import client.util.Console;
import client.util.SocketConnection;

public class MessageReceiver implements Runnable {
    private SocketConnection connection;
    private MessagePrinter messagePrinter;

    public MessageReceiver(
            SocketConnection connection,
            MessagePrinter messagePrinter
    ) {
        this.connection = connection;
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void run() {
        while (true) {
            if (this.connection.hasDataToReceive()) {
                String message, type, sender, content;

                message = this.connection.receiveLine();
                type = message.split(" ")[0];

                switch (type){
                    case "MSG": {
                        sender = message.split(" ", 3)[1];
                        content = message.split(" ", 3)[2];

                        this.messagePrinter.printMessage(sender, content);
                        break;
                    }
                    case "ERRO": {
                        content = message.split(" ", 2)[1];

                        this.messagePrinter.printErrorMessage(content);
                        break;
                    }
                }
            }
        }
    }
}
