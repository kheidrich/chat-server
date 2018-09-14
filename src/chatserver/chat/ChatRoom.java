package chatserver.chat;

import chatserver.message.MessageSender;

import java.util.ArrayList;

public class ChatRoom {
    private ArrayList<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
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

    public User[] getUsers() {
        return (User[]) this.users.toArray();
    }

    public User getUserByConnectionId(String id) {
        for (User u : this.users)
            if (u.getConnectionId() == id) return u;

        return null;
    }

    public boolean hasUserJoined(String connectionId){
        return this.getUserByConnectionId(connectionId) != null;
    }
}
