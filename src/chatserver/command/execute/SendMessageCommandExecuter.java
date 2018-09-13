package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class SendMessageCommandExecuter extends ChatCommandHandler {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger logger;

    public SendMessageCommandExecuter(ChatRoom chatRoom, MessageSender messageSender, MessageLogger logger) {
        super(chatRoom, messageSender);
        this.logger = logger;
    }

    @Override
    public void handle(ChatCommand command) {

    }
}
