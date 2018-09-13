package chatserver.chat;

public class Message {
    private String senderNickname;
    private String content;

    public Message(String senderId, String content) {
        this.senderNickname = senderId;
        this.content = content;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return  senderNickname + " " + content;
    }
}
