package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class SendMessageCommandHandler extends ChatCommandHandler {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger logger;

    public SendMessageCommandHandler(ChatRoom chatRoom, MessageSender messageSender, MessageLogger logger) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.logger = logger;
    }

    @Override
    public void handle(ChatCommand command) {

    }
}
