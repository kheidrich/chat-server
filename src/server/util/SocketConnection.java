package server.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection {
    private Socket socket;
    private Scanner input;
    private PrintWriter output;

    public SocketConnection(Socket socket) {
        this.socket = socket;
        try {
            this.input = new Scanner(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return Integer.toString(socket.hashCode());
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }

    public void close() {
        try {
            this.socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasDataToReceive() {
        return input.hasNextLine();
    }

    public String receiveLine() {
        return input.nextLine();
    }

    public void sendLine(String line) {
        output.println(line);
        output.flush();
    }
}
