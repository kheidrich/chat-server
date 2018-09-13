package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageSender;

public class LeaveCommandExecuter extends ChatCommandExecuter {
    private ChatRoom chatRoom;
    private MessageSender messageSender;

    public LeaveCommandExecuter(ChatRoom chatRoom, MessageSender messageSender) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
    }

    @Override
    public void execute(ChatCommand command) {

    }
}
