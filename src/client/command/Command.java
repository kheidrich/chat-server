package client.command;

import client.util.SocketConnection;

public class Command {
    private String type;
    private String parameter;

    public Command(String type, String parameter) {
        this.type = type;
        this.parameter = parameter;
    }

    public String getType() {
        return type;
    }

    public String getParameter() {
        return parameter;
    }
}
