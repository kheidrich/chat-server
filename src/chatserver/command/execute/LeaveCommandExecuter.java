package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.chat.User;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageSender;

import java.net.Socket;

public class LeaveCommandExecuter extends ChatCommandHandler {

    public LeaveCommandExecuter(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    private void communicateUserLeavedRoom(String nickname){
        for(User u : this.chatRoom.getUsers())
            this.messageSender.sendMessage(u.getConnectionId(), nickname + " saiu do grupo");
    }

    @Override
    public void handle(ChatCommand command) {
        User user;

        if(command.getType() == "SAIR") {
            user = this.chatRoom.getUserByConnectionId(command.getSenderId());
            this.chatRoom.leave(user);
            this.communicateUserLeavedRoom(user.getNickname());

            return;
        }

        this.executeNext(command);
    }
}
