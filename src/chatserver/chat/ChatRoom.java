package chatserver.chat;

import chatserver.message.MessageSender;

import java.util.ArrayList;

public class ChatRoom {
    private ArrayList<User> users;
    private MessageSender messageSender;

    public ChatRoom(MessageSender messageSender) {
        this.users = new ArrayList<>();
        this.messageSender = messageSender;
    }

    public void enter(User user) {
        this.users.add(user);
    }

    public void leave(User user) {
        this.users.remove(user);
    }

    public boolean isFull() {
        return this.users.size() == 100;
    }

    public boolean isNicknameInUse(String nickname) {
        for (User u : this.users)
            if (u.getNickname() == nickname) return true;

        return false;
    }

    public User getUserByConnectionId(String id) {
        for (User u : this.users)
            if (u.getConnectionId() == id) return u;

        return null;
    }

    public void sendMessageToRoom(Message message) {
        for (User u : this.users)
            this.messageSender.sendMessage(u.getConnectionId(), message.toString());
    }
}