package chatserver.chat;

import chatserver.command.*;
import chatserver.command.execute.ChatCommandExecuterFactory;
import chatserver.command.execute.EnterCommandExecuter;
import chatserver.command.execute.LeaveCommandExecuter;
import chatserver.command.execute.SendMessageCommandExecuter;
import chatserver.util.SocketConnection;

import java.util.List;

public class ChatConnectionHandler implements Runnable {
    private SocketConnection connection;
    private ChatCommandExecuterFactory commandExecuterFactory;
    private List<SocketConnection> activeConnections;

    public ChatConnectionHandler(
            SocketConnection connection,
            ChatCommandExecuterFactory commandExecuterFactory,
            List<SocketConnection> activeConnections
    ) {
        this.connection = connection;
        this.commandExecuterFactory = commandExecuterFactory;
        this.activeConnections = activeConnections;
    }

    @Override
    public void run() {
        this.activeConnections.add(this.connection);

        while (this.connection.isConnected()) {
            if (this.connection.hasDataToReceive()) {
                ChatCommand command;
                EnterCommandExecuter enterCommand = commandExecuterFactory.createEnterCommandExecuter();
                LeaveCommandExecuter leaveCommand = commandExecuterFactory.createLeaveCommandExecuter();
                SendMessageCommandExecuter sendMessageCommand = commandExecuterFactory.createSendMessageCommandExecuter();

                sendMessageCommand.setNext(enterCommand);
                enterCommand.setNext(leaveCommand);
                command = new ChatCommand(this.connection.getId(), this.connection.receiveLine());
                sendMessageCommand.handle(command);
            }
        }

        this.activeConnections.remove(this.connection);
    }
}
