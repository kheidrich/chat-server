package chatserver.chat;

import chatserver.command.*;
import chatserver.util.SocketConnection;

public class ChatConnectionHandler implements Runnable {
    private SocketConnection connection;
    private ChatCommandExecuterFactory commandExecuterFactory;

    public ChatConnectionHandler(SocketConnection connection, ChatCommandExecuterFactory commandExecuterFactory) {
        this.connection = connection;
        this.commandExecuterFactory = commandExecuterFactory;
    }

    @Override
    public void run() {
        while (this.connection.isConnected()) {
            if (this.connection.hasDataToReceive()) {
                ChatCommand command;
                EnterCommandExecuter enterCommand = commandExecuterFactory.createEnterCommandExecuter();
                LeaveCommandExecuter leaveCommand = commandExecuterFactory.createLeaveCommandExecuter();
                SendMessageCommandExecuter sendMessageCommand = commandExecuterFactory.createSendMessageCommandExecuter();

                sendMessageCommand.setNext(enterCommand);
                enterCommand.setNext(leaveCommand);
                command = new ChatCommand(this.connection.getId(), this.connection.receiveLine());
                sendMessageCommand.execute(command);
            }
        }
    }
}
