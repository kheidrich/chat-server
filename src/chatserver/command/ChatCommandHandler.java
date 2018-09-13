package chatserver.command;

public abstract class ChatCommandHandler {
    protected ChatCommandHandler next;

    protected void executeNext(ChatCommand command){
        if(this.next != null)
            next.handle(command);
    }

    public void setNext(ChatCommandHandler next) {
        this.next = next;
    }

    public abstract void handle(ChatCommand command);
}
