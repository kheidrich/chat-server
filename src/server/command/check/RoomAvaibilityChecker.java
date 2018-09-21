package server.command.check;

import server.chat.ChatRoom;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;

public class RoomAvaibilityChecker extends ChatCommandHandler {

    public RoomAvaibilityChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if (command.getType().equals("ENTRAR") && this.chatRoom.isFull()) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Sala está cheia");
            return;
        }

        executeNext(command);
    }
}
