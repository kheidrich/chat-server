package chatserver.command;

import java.util.Arrays;
import java.util.List;

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

    public String[] getParameters() {
        List<String> splitedCommand = Arrays.asList(this.args.split(" "));
        String[] args;

        splitedCommand.remove(0);
        args = new String[splitedCommand.size()];
        splitedCommand.toArray(args);

        return args;
    }
}
