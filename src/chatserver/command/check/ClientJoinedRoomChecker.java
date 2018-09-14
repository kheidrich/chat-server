package chatserver.command.check;

import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageSender;

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
