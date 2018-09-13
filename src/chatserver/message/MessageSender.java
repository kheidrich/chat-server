package chatserver.message;

import chatserver.util.SocketConnection;

import java.util.ArrayList;

public class MessageSender {
    private ArrayList<SocketConnection> connections;

    public MessageSender() {
        this.connections = new ArrayList<>();
    }

    private SocketConnection findConnectionById(String id) {
        SocketConnection connection = null;

        for (SocketConnection conn : this.connections)
            if (conn.getId() == id) {
                connection = conn;
                break;
            }

        return connection;
    }

    public void addConection(SocketConnection connection) {
        this.connections.add(connection);
    }

    public void removeConnection(SocketConnection connection) {
        this.connections.remove(connection);
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
