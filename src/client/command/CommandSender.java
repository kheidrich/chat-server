package client.command;

import client.util.SocketConnection;

public class CommandSender {
    private SocketConnection connection;

    public CommandSender(SocketConnection connection) {
        this.connection = connection;
    }

    public void sendEnterCommand(String nickname) {
        this.connection.sendLine("ENTRAR " + nickname);
    }

    public void sendMessageCommand(String content) {
        this.connection.sendLine("MSG " + content);
    }

    public void sendLeaveCommand() {
        this.connection.sendLine("SAIR");
    }
}
