package server.message;

import server.chat.Message;

import java.io.*;
import java.util.ArrayList;

public class MessageLogger {
    private File logFile;

    public MessageLogger(File logFile) {
        this.logFile = logFile;
    }

    private ArrayList<Message> readLog() {
        ObjectInputStream logReader = null;
        ArrayList<Message> messages;

        try {
            logReader = new ObjectInputStream(new FileInputStream(this.logFile));
            messages = (ArrayList<Message>) logReader.readObject();

            return messages;
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            try {
                if (logReader != null) logReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void addMessage(Message message) {
        ObjectOutputStream logWriter;
        ArrayList<Message> log;

        try {
            log = this.readLog();
            log.add(message);
            logWriter = new ObjectOutputStream(new FileOutputStream(this.logFile));
            logWriter.writeObject(log);
            logWriter.flush();
            logWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message[] getLastMessages(int quantity) {
        Message[] messages;
        ArrayList<Message> log;
        int messagesToSend = quantity;

        log = this.readLog();
        if (log.size() < quantity) messagesToSend = log.size();
        messages = new Message[messagesToSend];

        for (int i = 0; i < messagesToSend; i++)
            messages[i] = log.get(i + log.size() - messagesToSend);

        return messages;
    }
}
