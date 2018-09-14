package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.chat.Message;
import chatserver.chat.User;
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
        User[] receivers;
        Message messageToSend;
        String content;

        if(command.getType() == "MSG") {
            content = command.getParameter();
            messageToSend = new Message(command.getSenderId(), content);
            receivers = this.chatRoom.getUsers();

            for(User u : receivers)
                this.messageSender.sendMessage(u.getConnectionId(), messageToSend.toString());

            return;
        }

        this.executeNext(command);
    }
}
