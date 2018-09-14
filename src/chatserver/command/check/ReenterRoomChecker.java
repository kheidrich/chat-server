package chatserver.command.check;

import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageSender;

public class ReenterRoomChecker extends ChatCommandHandler {

    public ReenterRoomChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if (command.getType() == "ENTRAR" && this.chatRoom.hasUserJoined(command.getSenderId())) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Cliente já entrou na sala");
            return;
        }

        this.executeNext(command);
    }
}
