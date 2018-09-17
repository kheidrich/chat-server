package chatserver.chat;

import chatserver.command.*;
import chatserver.command.check.*;
import chatserver.command.execute.*;
import chatserver.util.SocketConnection;

import java.util.List;

public class ChatConnectionHandler implements Runnable {
    private SocketConnection connection;
    private List<SocketConnection> activeConnections;
    private ChatCommandExecuterFactory commandExecuterFactory;
    private ChatCommandCheckerFactory commandCheckerFactory;

    public ChatConnectionHandler(
            SocketConnection connection,
            List<SocketConnection> activeConnections,
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

        invalidCommandChecker.setNext(roomAvaibilityChecker);
        roomAvaibilityChecker.setNext(nicknameAvaibilityChecker);
        nicknameAvaibilityChecker.setNext(reenterRoomChecker);
        reenterRoomChecker.setNext(enterCommandExecuter);
        enterCommandExecuter.setNext(clientJoinedRoomChecker);
        clientJoinedRoomChecker.setNext(sendMessageCommandExecuter);
        sendMessageCommandExecuter.setNext(leaveCommandExecuter);
        leaveCommandExecuter.setNext(roomAvaibilityChecker);

        this.activeConnections.add(this.connection);
        while (this.connection.isConnected()) {
            if (this.connection.hasDataToReceive()) {
                ChatCommand command;

                command = new ChatCommand(this.connection.getId(), this.connection.receiveLine());
                invalidCommandChecker.handle(command);
            }
        }
        this.activeConnections.remove(this.connection);
    }
}
