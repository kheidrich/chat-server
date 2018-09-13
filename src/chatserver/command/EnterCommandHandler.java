package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.chat.User;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class EnterCommandHandler extends ChatCommandHandler {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger messageLogger;

    public EnterCommandHandler(ChatRoom chatRoom, MessageSender messageSender, MessageLogger messageLogger) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.messageLogger = messageLogger;
    }

    @Override
    public void handle(ChatCommand command) {
        String nickname = command.getParameters()[0];

        if (command.getType() == "ENTRAR") {
            if (this.chatRoom.isFull()) {
                this.messageSender.sendErrorMessage(command.getSenderId(), "Sala está cheia!");
                return;
            }

            if (this.chatRoom.isNicknameInUse(nickname)) {
                this.messageSender.sendErrorMessage(command.getSenderId(), "Já existe outro usuário com o mesmo nickname");
                return;
            }

            for(User u : this.chatRoom.getUsers())
                this.messageSender.sendMessage(u.getConnectionId(), nickname + " entrou no grupo");

            this.chatRoom.enter(new User(command.getSenderId(), nickname));

            return;
        }

        this.executeNext(command);
    }
}
