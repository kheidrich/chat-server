package chatserver.command;

import java.util.Arrays;
import java.util.List;

public class ChatCommand {
    private String senderId;
    private String command;

    public ChatCommand(String senderId, String command) {
        this.senderId = senderId;
        this.command = command;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public String getType() {
        return this.command.split(" ")[0];
    }

    public String[] getArgs() {
        List<String> splitedCommand = Arrays.asList(this.command.split(" "));
        String[] args;

        splitedCommand.remove(0);
        args = new String[splitedCommand.size()];
        splitedCommand.toArray(args);

        return args;
    }
}
