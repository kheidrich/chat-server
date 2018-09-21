package server.chat;

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
            if (u.getNickname().equals(nickname)) return true;

        return false;
    }

    public User[] getUsers() {
        User[] destination = new User[this.users.size()];

        this.users.toArray(destination);
        return destination;
    }

    public User getUserByConnectionId(String id) {
        for (User u : this.users)
            if (u.getConnectionId().equals(id)) return u;

        return null;
    }

    public boolean hasUserJoined(String connectionId) {
        return this.getUserByConnectionId(connectionId) != null;
    }
}
