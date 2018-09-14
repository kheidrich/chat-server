package chatserver.command.execute;

import chatserver.chat.ChatRoom;
import chatserver.chat.Message;
import chatserver.chat.User;
import chatserver.command.ChatCommand;
import chatserver.command.ChatCommandHandler;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;

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
        String nickname = command.getParameter();

        if (command.getType() == "ENTRAR") {
            this.communicateUserJoinedRoom(nickname);
            this.sendMessageHistoryToUser(command.getSenderId());
            this.chatRoom.enter(new User(command.getSenderId(), nickname));

            return;
        }

        this.executeNext(command);
    }
}
