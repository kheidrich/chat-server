package chatserver.command.check;

import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageSender;

public class NicknameAvaibilityChecker extends ChatCommandHandler {

    public NicknameAvaibilityChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        if (command.getType() == "ENTRAR") {
            String nickname = command.getParameter();

            if (this.chatRoom.isNicknameInUse(nickname)) {
                this.messageSender.sendErrorMessage(command.getSenderId(), "Existe outro cliente com o mesmo nickname");
                return;
            }
        }

        this.executeNext(command);
    }
}
