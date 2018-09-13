package chatserver;

import chatserver.chat.ChatConnectionHandler;
import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommandHandlerFactory;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;
import chatserver.util.SocketConnection;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<SocketConnection> activeConnections = new ArrayList<>();
        MessageSender messageSender = new MessageSender(activeConnections);
        ChatRoom chatRoom = new ChatRoom(messageSender);
        MessageLogger messageLogger = new MessageLogger(new File("./messages.log"));
        ChatCommandHandlerFactory commandHandlerFactory = new ChatCommandHandlerFactory(chatRoom, messageSender, messageLogger);

        try(ServerSocket server = new ServerSocket(8976)) {

            while (true) {
                SocketConnection connection = new SocketConnection(server.accept());
                ChatConnectionHandler handler = new ChatConnectionHandler(connection, commandHandlerFactory, activeConnections);

                (new Thread(handler)).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
