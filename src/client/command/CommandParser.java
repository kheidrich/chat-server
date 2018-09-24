package client.command;


public class CommandParser {
    private boolean isChatCommand(String command) {
        return command.trim().startsWith("/");
    }

    public Command parse(String command) {
        if (isChatCommand(command)) {
            String type = command.trim().split(" ", 2)[0].substring(1);
            String parameter = "";
            boolean hasParameter = command.trim().split(" ", 2).length == 2;

            if(hasParameter) parameter = command.trim().split(" ", 2)[1];

            return new Command(type, parameter);
        } else
            return new Command("message", command);
    }
}
