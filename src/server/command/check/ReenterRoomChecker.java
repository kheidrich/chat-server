package server.command.check;

import server.chat.ChatRoom;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;

public class ReenterRoomChecker extends ChatCommandHandler {

    public ReenterRoomChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if (command.getType().equals("ENTRAR") && this.chatRoom.hasUserJoined(command.getSenderId())) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Cliente já entrou na sala");
            return;
        }

        this.executeNext(command);
    }
}
