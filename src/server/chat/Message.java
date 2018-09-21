package server.chat;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String senderNickname;
    private String content;

    public Message(String senderNickname, String content) {
        this.senderNickname = senderNickname;
        this.content = content;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return senderNickname + " " + content;
    }
}
