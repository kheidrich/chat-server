package test;

import chatserver.Main;
import chatserver.util.SocketConnection;

import java.io.IOException;
import java.net.Socket;

public class Teste {

    public static void main(String[] args) {
        try {
            SocketConnection client = new SocketConnection(new Socket("localhost", 8976));

            client.sendLine("MSG dsdadadad");
            while (!client.hasDataToReceive());
            System.out.println(client.receiveLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
