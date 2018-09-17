package chatserver.message;

import chatserver.util.SocketConnection;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private List<SocketConnection> activeConnections;

    public MessageSender(List<SocketConnection> activeConnections) {
        this.activeConnections = activeConnections;
    }

    private SocketConnection findConnectionById(String id) {
        SocketConnection connection = null;

        for (SocketConnection conn : this.activeConnections)
            if (conn.getId().equals(id)) {
                connection = conn;
                break;
            }

        return connection;
    }

    public void sendMessage(String receiverId, String message) {
        SocketConnection receiver = this.findConnectionById(receiverId);

        if (receiver != null)
            receiver.sendLine("MSG " + message);
    }

    public void sendErrorMessage(String receiverId, String message) {
        SocketConnection receiver = this.findConnectionById(receiverId);

        if (receiver != null)
            receiver.sendLine("ERRO " + message);
    }
}
