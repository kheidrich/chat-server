package server.command.check;

import server.chat.ChatRoom;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;

public class ClientJoinedRoomChecker extends ChatCommandHandler {
    public ClientJoinedRoomChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if(!this.chatRoom.hasUserJoined(command.getSenderId())) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Cliente não entrou na sala");
            return;
        }

        this.executeNext(command);
    }
}
