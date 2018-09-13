package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageSender;

public class LeaveCommandHandler extends ChatCommandHandler {
    private ChatRoom chatRoom;
    private MessageSender messageSender;

    public LeaveCommandHandler(ChatRoom chatRoom, MessageSender messageSender) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
    }

    @Override
    public void handle(ChatCommand command) {

    }
}
