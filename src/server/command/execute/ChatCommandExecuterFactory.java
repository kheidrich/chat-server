package server.command.execute;

import server.chat.ChatRoom;
import server.message.MessageLogger;
import server.message.MessageSender;
import server.util.ActiveConnectionsList;

public class ChatCommandExecuterFactory {
    private ChatRoom chatRoom;
    private MessageSender messageSender;
    private MessageLogger messageLogger;
    private ActiveConnectionsList activeConnections;

    public ChatCommandExecuterFactory(
            ChatRoom chatRoom,
            MessageSender messageSender,
            MessageLogger messageLogger,
            ActiveConnectionsList activeConnections
    ) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
        this.messageLogger = messageLogger;
        this.activeConnections = activeConnections;
    }

    public SendMessageCommandExecuter createSendMessageCommandExecuter() {
        return new SendMessageCommandExecuter(this.chatRoom, this.messageSender, this.messageLogger);
    }

    public EnterCommandExecuter createEnterCommandExecuter() {
        return new EnterCommandExecuter(this.chatRoom, this.messageSender, this.messageLogger);
    }

    public LeaveCommandExecuter createLeaveCommandExecuter() {
        return new LeaveCommandExecuter(this.chatRoom, this.messageSender, this.activeConnections);
    }


}
