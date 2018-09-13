package chatserver.command.check;

import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageSender;

public class RoomAvaibilityChecker extends ChatCommandHandler {

    public RoomAvaibilityChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if (command.getType() == "ENTRAR" && this.chatRoom.isFull()) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Sala está cheia");
            return;
        }

        executeNext(command);
    }
}
