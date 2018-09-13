package chatserver.command;

public abstract class ChatCommandExecuter {
    protected ChatCommandExecuter next;

    protected void executeNext(ChatCommand command){
        if(this.next != null)
            next.execute(command);
    }

    public void setNext(ChatCommandExecuter next) {
        this.next = next;
    }

    public abstract void execute(ChatCommand command);
}
