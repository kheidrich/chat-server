package server.chat;

import server.command.*;
import server.command.check.*;
import server.command.execute.*;
import server.util.ActiveConnectionsList;
import server.util.SocketConnection;

public class ChatConnectionHandler implements Runnable {
    private SocketConnection connection;
    private ActiveConnectionsList activeConnections;
    private ChatCommandExecuterFactory commandExecuterFactory;
    private ChatCommandCheckerFactory commandCheckerFactory;

    public ChatConnectionHandler(
            SocketConnection connection,
            ActiveConnectionsList activeConnections,
            ChatCommandExecuterFactory commandExecuterFactory,
            ChatCommandCheckerFactory commandCheckerFactory
    ) {
        this.connection = connection;
        this.activeConnections = activeConnections;
        this.commandExecuterFactory = commandExecuterFactory;
        this.commandCheckerFactory = commandCheckerFactory;
    }

    @Override
    public void run() {
        EnterCommandExecuter enterCommandExecuter = commandExecuterFactory.createEnterCommandExecuter();
        LeaveCommandExecuter leaveCommandExecuter = commandExecuterFactory.createLeaveCommandExecuter();
        SendMessageCommandExecuter sendMessageCommandExecuter = commandExecuterFactory.createSendMessageCommandExecuter();
        InvalidCommandChecker invalidCommandChecker = commandCheckerFactory.createInvalidCommandChecker();
        RoomAvaibilityChecker roomAvaibilityChecker = commandCheckerFactory.createRoomAvaibilityChecker();
        NicknameAvaibilityChecker nicknameAvaibilityChecker = commandCheckerFactory.createNicknameAvaibilityChecker();
        ReenterRoomChecker reenterRoomChecker = commandCheckerFactory.createReenterRoomChecker();
        ClientJoinedRoomChecker clientJoinedRoomChecker = commandCheckerFactory.createClientJoinedRoomChecker();

        invalidCommandChecker.setNext(reenterRoomChecker);
        reenterRoomChecker.setNext(roomAvaibilityChecker);
        roomAvaibilityChecker.setNext(nicknameAvaibilityChecker);
        nicknameAvaibilityChecker.setNext(enterCommandExecuter);
        enterCommandExecuter.setNext(clientJoinedRoomChecker);
        clientJoinedRoomChecker.setNext(sendMessageCommandExecuter);
        sendMessageCommandExecuter.setNext(leaveCommandExecuter);

        synchronized (this.activeConnections) {
            this.activeConnections.addConnection(this.connection);
        }
        while (!this.connection.isClosed()) {
            if (this.connection.hasDataToReceive()) {
                ChatCommand command;

                command = new ChatCommand(this.connection.getId(), this.connection.receiveLine());
                invalidCommandChecker.handle(command);
            }
        }
        synchronized (this.activeConnections) {
            this.activeConnections.removeConnection(this.connection);
        }
    }
}
