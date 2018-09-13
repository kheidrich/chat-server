package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class SendMessageCommandExecuter extends ChatCommandExecuter {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger logger;

    public SendMessageCommandExecuter(ChatRoom chatRoom, MessageSender messageSender, MessageLogger logger) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.logger = logger;
    }

    @Override
    public void execute(ChatCommand command) {

    }
}
