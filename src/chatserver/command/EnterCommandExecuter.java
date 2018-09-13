package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class EnterCommandExecuter extends ChatCommandExecuter{
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger logger;

    public EnterCommandExecuter(ChatRoom chatRoom, MessageSender messageSender, MessageLogger logger) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.logger = logger;
    }

    @Override
    public void execute(ChatCommand command) {
        if(command.getType() == "ENTRAR") {

        }

        this.executeNext(command);
    }
}
