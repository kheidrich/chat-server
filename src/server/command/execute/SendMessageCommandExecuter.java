package server.command.execute;

import server.chat.ChatRoom;
import server.chat.Message;
import server.chat.User;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageLogger;
import server.message.MessageSender;

public class SendMessageCommandExecuter extends ChatCommandHandler {
    private MessageLogger logger;

    public SendMessageCommandExecuter(ChatRoom chatRoom, MessageSender messageSender, MessageLogger logger) {
        super(chatRoom, messageSender);
        this.logger = logger;
    }

    @Override
    public void handle(ChatCommand command) {
        User[] receivers;
        Message messageToSend;
        User sender;
        String content;

        if(command.getType().equals("MSG")) {
            content = command.getParameter();
            sender = this.chatRoom.getUserByConnectionId(command.getSenderId());
            messageToSend = new Message(sender.getNickname(), content);
            receivers = this.chatRoom.getUsers();

            for(User u : receivers)
                this.messageSender.sendMessage(u.getConnectionId(), messageToSend.toString());

            this.logger.addMessage(messageToSend);

            return;
        }

        this.executeNext(command);
    }
}
