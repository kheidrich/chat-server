package server.command.check;

import server.chat.ChatRoom;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;

public class NicknameAvaibilityChecker extends ChatCommandHandler {

    public NicknameAvaibilityChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if (command.getType().equals("ENTRAR")) {
            String nickname = command.getParameter();

            if (this.chatRoom.isNicknameInUse(nickname)) {
                this.messageSender.sendErrorMessage(command.getSenderId(), "Existe outro cliente com o mesmo nickname");
                return;
            }
        }

        this.executeNext(command);
    }
}
