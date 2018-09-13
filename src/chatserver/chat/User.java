package chatserver.chat;

import chatserver.command.*;
import chatserver.util.SocketConnection;

public class User {
    private String connectionId;
    private String nickname;

    public User(String connectionId, String nickname) {
        this.connectionId = connectionId;
        this.nickname = nickname;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public String getNickname() {
        return nickname;
    }


}
