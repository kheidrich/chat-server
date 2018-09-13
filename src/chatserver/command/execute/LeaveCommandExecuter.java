package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageSender;

public class LeaveCommandExecuter extends ChatCommandHandler {

    public LeaveCommandExecuter(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {

    }
}
