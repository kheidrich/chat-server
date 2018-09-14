package chatserver.command.check;

import chatserver.chat.ChatRoom;
import chatserver.message.MessageSender;

public class ChatCommandCheckerFactory {
    private ChatRoom chatRoom;
    private MessageSender messageSender;

    public ChatCommandCheckerFactory(ChatRoom chatRoom, MessageSender messageSender) {
        this.chatRoom = chatRoom;
        this.messageSender = messageSender;
    }

    public InvalidCommandChecker createInvalidCommandChecker() {
        return new InvalidCommandChecker(this.chatRoom, this.messageSender);
    }

    public RoomAvaibilityChecker createRoomAvaibilityChecker() {
        return new RoomAvaibilityChecker(this.chatRoom, this.messageSender);
    }

    public NicknameAvaibilityChecker createNicknameAvaibilityChecker() {
        return new NicknameAvaibilityChecker(this.chatRoom, this.messageSender);
    }

    public ReenterRoomChecker createReenterRoomChecker() {
        return new ReenterRoomChecker(this.chatRoom, this.messageSender);
    }

    public ClientJoinedRoomChecker createClientJoinedRoomChecker() {
        return  new ClientJoinedRoomChecker(this.chatRoom, this.messageSender);
    }
}
