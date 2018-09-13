package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class ChatCommandHandlerFactory {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger messageLogger;

    public ChatCommandHandlerFactory(ChatRoom chatRoom, MessageSender messageSender, MessageLogger messageLogger) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.messageLogger = messageLogger;
    }

    public SendMessageCommandHandler createSendMessageCommandHandler() {
        return new SendMessageCommandHandler(this.chatRoom, this.messageSender, this.messageLogger);
    }

    public EnterCommandHandler createEnterCommandHandler() {
        return new EnterCommandHandler(this.chatRoom, this.messageSender, this.messageLogger);
    }

    public LeaveCommandHandler createLeaveCommandHandler() {
        return new LeaveCommandHandler(this.chatRoom, this.messageSender);
    }
}
