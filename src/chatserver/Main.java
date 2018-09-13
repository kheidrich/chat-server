package chatserver;

import chatserver.chat.ChatConnectionHandler;
import chatserver.chat.ChatRoom;
import chatserver.command.ChatCommandExecuterFactory;
import chatserver.message.MessageLogger;
import chatserver.message.MessageSender;
import chatserver.util.SocketConnection;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        MessageSender messageSender = new MessageSender();
        ChatRoom chatRoom = new ChatRoom(messageSender);
        MessageLogger messageLogger = new MessageLogger(new File("./messages.log"));
        ChatCommandExecuterFactory commandExecuterFactory = new ChatCommandExecuterFactory(chatRoom, messageSender, messageLogger);

        try(ServerSocket server = new ServerSocket(8976)) {

            while (true) {
                SocketConnection connection = new SocketConnection(server.accept());
                ChatConnectionHandler handler = new ChatConnectionHandler(connection, commandExecuterFactory);

                (new Thread(handler)).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
