package chatserver.command;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageSender;

public abstract class ChatCommandHandler {
    protected ChatRoom chatRoom;
    protected MessageSender messageSender;
    protected ChatCommandHandler next;

    public ChatCommandHandler(ChatRoom chatRoom, MessageSender messageSender) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
    }

    protected void executeNext(ChatCommand command){
        if(this.next != null)
            next.handle(command);
    }

    public void setNext(ChatCommandHandler next) {
        this.next = next;
    }

    public abstract void handle(ChatCommand command);
}
