package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

public class ChatCommandExecuterFactory {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger messageLogger;

    public ChatCommandExecuterFactory(ChatRoom chatRoom, MessageSender messageSender, MessageLogger messageLogger) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.messageLogger = messageLogger;
    }

    public SendMessageCommandExecuter createSendMessageCommandExecuter() {
        return new SendMessageCommandExecuter(this.chatRoom, this.messageSender, this.messageLogger);
    }

    public EnterCommandExecuter createEnterCommandExecuter() {
        return new EnterCommandExecuter(this.chatRoom, this.messageSender, this.messageLogger);
    }

    public LeaveCommandExecuter createLeaveCommandExecuter() {
        return new LeaveCommandExecuter(this.chatRoom, this.messageSender);
    }
}
