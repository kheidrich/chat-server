package server.command.execute;

import server.chat.ChatRoom;
import server.chat.Message;
import server.chat.User;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageLogger;
import server.message.MessageSender;

public class EnterCommandExecuter extends ChatCommandHandler {
    private MessageLogger messageLogger;

    public EnterCommandExecuter(ChatRoom chatRoom, MessageSender messageSender, MessageLogger messageLogger) {
        super(chatRoom, messageSender);
        this.messageLogger = messageLogger;
    }

    private void communicateUserJoinedRoom(String nickname) {
        for (User u : this.chatRoom.getUsers())
            this.messageSender.sendMessage(u.getConnectionId(), nickname + " entrou no grupo");
    }

    private void sendMessageHistoryToUser(String connectionId) {
        Message[] messageHistory = this.messageLogger.getLastMessages(20);

        for(Message m : messageHistory)
            this.messageSender.sendMessage(connectionId, m.toString());
    }

    @Override
    public void handle(ChatCommand command) {

        if (command.getType().equals("ENTRAR")) {
            String nickname = command.getParameter();

            this.communicateUserJoinedRoom(nickname);
            this.sendMessageHistoryToUser(command.getSenderId());
            synchronized (this.chatRoom) {
                this.chatRoom.enter(new User(command.getSenderId(), nickname));
            }

            return;
        }

        this.executeNext(command);
    }
}
