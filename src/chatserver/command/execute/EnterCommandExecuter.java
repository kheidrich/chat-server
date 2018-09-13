package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.chat.User;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class EnterCommandExecuter extends ChatCommandHandler {
    private MessageLogger messageLogger;

    public EnterCommandExecuter(ChatRoom chatRoom, MessageSender messageSender, MessageLogger messageLogger) {
        super(chatRoom, messageSender);
        this.messageLogger = messageLogger;
    }

    @Override
    public void handle(ChatCommand command) {
        String nickname = command.getParameters()[0];

        if (command.getType() == "ENTRAR") {
            for (User u : this.chatRoom.getUsers())
                this.messageSender.sendMessage(u.getConnectionId(), nickname + " entrou no grupo");
            this.chatRoom.enter(new User(command.getSenderId(), nickname));
            return;
        }

        this.executeNext(command);
    }
}
