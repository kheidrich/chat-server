package chatserver;

import chatserver.chat.ChatConnectionHandler;
import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommand;
import chatserver.command.check.ChatCommandCheckerFactory;
import chatserver.command.execute.ChatCommandExecuterFactory;
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
        ChatRoom chatRoom = new ChatRoom();
        MessageSender messageSender = new MessageSender(activeConnections);
        MessageLogger messageLogger = new MessageLogger(new File("./messages.log"));
        ChatCommandExecuterFactory commandExecuterFactory = new ChatCommandExecuterFactory(chatRoom, messageSender, messageLogger);
        ChatCommandCheckerFactory commandCheckerFactory = new ChatCommandCheckerFactory(chatRoom, messageSender);

        try(ServerSocket server = new ServerSocket(8976)) {

            while (true) {
                SocketConnection connection = new SocketConnection(server.accept());
                ChatConnectionHandler handler = new ChatConnectionHandler(connection, activeConnections, commandExecuterFactory, commandCheckerFactory);

                (new Thread(handler)).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
