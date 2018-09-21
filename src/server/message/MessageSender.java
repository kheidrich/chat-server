package server.message;

import server.util.ActiveConnectionsList;
import server.util.SocketConnection;

public class MessageSender {
    private ActiveConnectionsList activeConnections;

    public MessageSender(ActiveConnectionsList activeConnections) {
        this.activeConnections = activeConnections;
    }

    public void sendMessage(String receiverId, String message) {
        SocketConnection receiver = this.activeConnections.findById(receiverId);

        if (receiver != null)
            receiver.sendLine("MSG " + message);
    }

    public void sendErrorMessage(String receiverId, String message) {
        SocketConnection receiver = this.activeConnections.findById(receiverId);

        if (receiver != null)
            receiver.sendLine("ERRO " + message);
    }
}
