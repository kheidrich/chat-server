package chatserver.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection {
    private Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
    }

    public String getId() {
        return Integer.toString(socket.hashCode());
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public  boolean hasDataToReceive(){
        try {
            Scanner input = new Scanner(new InputStreamReader(this.socket.getInputStream()));
            boolean has;

            has = input.hasNextLine();
//            input.close();

            return has;
        }
        catch (IOException e){
            e.printStackTrace();

            return false;
        }
    }

    public String receiveLine(){
        try {
            Scanner input = new Scanner(new InputStreamReader(this.socket.getInputStream()));
            String line;

            line = input.nextLine();
//            input.close();

            return line;
        }
        catch (IOException e){
            e.printStackTrace();

            return "";
        }
    }

    public void sendLine(String line){
        try {
            PrintWriter output = new PrintWriter(this.socket.getOutputStream());

            output.println(line);
            output.flush();
            output.close();
        } catch (IOException e) {
               e.printStackTrace();
        }
    }
}
