package server.message;

import server.chat.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MessageLogger {
    private File logFile;

    public MessageLogger(File logFile) {
        this.logFile = logFile;
    }

    private Message parseMessage(String line) {
        String sender, content;

        sender = line.split(" ", 2)[0];
        content = line.split(" ", 2)[1];

        return new Message(sender, content);
    }

    private ArrayList<Message> readLog() {
        Scanner logReader = null;
        ArrayList<Message> messages = new ArrayList<>();

        try {
            logReader = new Scanner(new FileInputStream(this.logFile));

            while (logReader.hasNextLine())
                messages.add(this.parseMessage(logReader.nextLine()));

            return messages;
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            if (logReader != null) logReader.close();
        }
    }


    public synchronized void addMessage(Message message) {
        PrintWriter logWriter;

        try {
            logWriter = new PrintWriter(new FileOutputStream(this.logFile, true));
            logWriter.println(message.getSenderNickname() + " " + message.getContent());
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
