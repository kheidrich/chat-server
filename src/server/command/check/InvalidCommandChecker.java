package server.command.check;

import server.chat.ChatRoom;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;

import java.util.ArrayList;

public class InvalidCommandChecker extends ChatCommandHandler {
    public InvalidCommandChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
    }

    @Override
    public void handle(ChatCommand command) {
        ArrayList<String> validCommands = new ArrayList<>();

        validCommands.add("ENTRAR");
        validCommands.add("MSG");
        validCommands.add("SAIR");

        if (!validCommands.contains(command.getType())) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Comando inválido");
            return;
        }

        this.executeNext(command);
    }
}