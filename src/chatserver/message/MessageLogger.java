package chatserver.message;

import chatserver.chat.Message;

import java.io.File;

public class MessageLogger {
    private File logFile;

    public  MessageLogger(File logFile) {
        this.logFile = logFile;
    }

    public void addMessage(Message message) {

    }

    public Message[] getLastMessages(int quantity) {
        return null;
    }
}
