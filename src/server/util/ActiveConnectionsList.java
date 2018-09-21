package server.util;

import java.util.ArrayList;
import java.util.List;

public class ActiveConnectionsList {
    private List<SocketConnection> connections;
    private static ActiveConnectionsList instance;

    private ActiveConnectionsList() {
        this.connections = new ArrayList<>();
    }

    public static ActiveConnectionsList getInstance() {
        if(ActiveConnectionsList.instance == null)
            instance = new ActiveConnectionsList();

        return instance;
    }

    public void addConnection(SocketConnection connection) {
        this.connections.add(connection);
    }

    public void removeConnection(SocketConnection connection) {
        this.connections.remove(connection);
    }

    public SocketConnection findById(String id) {
        SocketConnection connection = null;

        for (SocketConnection conn : this.connections)
            if (conn.getId().equals(id)) {
                connection = conn;
                break;
            }

        return connection;
    }
}
