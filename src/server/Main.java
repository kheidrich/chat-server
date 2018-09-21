package server;

import server.chat.ChatConnectionHandler;
import server.chat.ChatRoom;
import server.command.check.ChatCommandCheckerFactory;
import server.command.execute.ChatCommandExecuterFactory;
import server.message.MessageLogger;
import server.message.MessageSender;
import server.util.ActiveConnectionsList;
import server.util.SocketConnection;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        File messageLog = new File("./messages.log");
        ChatRoom chatRoom = new ChatRoom();
        MessageSender messageSender = new MessageSender(ActiveConnectionsList.getInstance());
        MessageLogger messageLogger = new MessageLogger(messageLog);
        ChatCommandExecuterFactory commandExecuterFactory = new ChatCommandExecuterFactory(chatRoom, messageSender, messageLogger, ActiveConnectionsList.getInstance());
        ChatCommandCheckerFactory commandCheckerFactory = new ChatCommandCheckerFactory(chatRoom, messageSender);

        try (ServerSocket server = new ServerSocket(8976)) {
            if (!messageLog.exists()) messageLog.createNewFile();
            while (true) {
                SocketConnection connection = new SocketConnection(server.accept());
                ChatConnectionHandler handler = new ChatConnectionHandler(connection, ActiveConnectionsList.getInstance(), commandExecuterFactory, commandCheckerFactory);

                (new Thread(handler)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
