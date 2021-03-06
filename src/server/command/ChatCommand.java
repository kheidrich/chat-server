package server.command;

public class ChatCommand {
    private String senderId;
    private String args;

    public ChatCommand(String senderId, String args) {
        this.senderId = senderId;
        this.args = args;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public String getType() {
        return this.args.split(" ")[0];
    }

    public String getParameter() {
        return this.args.split(" ", 2)[1];
    }
}
