package server.command.execute;

import server.chat.ChatRoom;
import server.chat.User;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;
import server.util.ActiveConnectionsList;

public class LeaveCommandExecuter extends ChatCommandHandler {
    private ActiveConnectionsList activeConnections;

    public LeaveCommandExecuter(
            ChatRoom chatRoom,
            MessageSender messageSender,
            ActiveConnectionsList activeConnectionsList
    ) {
        super(chatRoom, messageSender);
        this.activeConnections = activeConnectionsList;
    }

    private void communicateUserLeavedRoom(String nickname) {
        for (User u : this.chatRoom.getUsers())
            this.messageSender.sendMessage(u.getConnectionId(), nickname + " saiu do grupo");
    }

    @Override
    public void handle(ChatCommand command) {
        User user;

        if (command.getType().equals("SAIR")) {
            user = this.chatRoom.getUserByConnectionId(command.getSenderId());

            synchronized (this.chatRoom) {
                this.chatRoom.leave(user);
            }
            this.activeConnections.findById(user.getConnectionId()).close();
            this.communicateUserLeavedRoom(user.getNickname());

            return;
        }

        this.executeNext(command);
    }
}
