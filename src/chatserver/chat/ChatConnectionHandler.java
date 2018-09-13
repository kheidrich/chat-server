package chatserver.chat;

import chatserver.command.*;
import chatserver.util.SocketConnection;

import java.util.List;

public class ChatConnectionHandler implements Runnable {
    private SocketConnection connection;
    private ChatCommandHandlerFactory commandHandlerFactory;
    private List<SocketConnection> activeConnections;

    public ChatConnectionHandler(
            SocketConnection connection,
            ChatCommandHandlerFactory commandHandlerFactory,
            List<SocketConnection> activeConnections
    ) {
        this.connection = connection;
        this.commandHandlerFactory = commandHandlerFactory;
        this.activeConnections = activeConnections;
    }

    @Override
    public void run() {
        this.activeConnections.add(this.connection);

        while (this.connection.isConnected()) {
            if (this.connection.hasDataToReceive()) {
                ChatCommand command;
                EnterCommandHandler enterCommand = commandHandlerFactory.createEnterCommandHandler();
                LeaveCommandHandler leaveCommand = commandHandlerFactory.createLeaveCommandHandler();
                SendMessageCommandHandler sendMessageCommand = commandHandlerFactory.createSendMessageCommandHandler();

                sendMessageCommand.setNext(enterCommand);
                enterCommand.setNext(leaveCommand);
                command = new ChatCommand(this.connection.getId(), this.connection.receiveLine());
                sendMessageCommand.handle(command);
            }
        }

        this.activeConnections.remove(this.connection);
    }
}
