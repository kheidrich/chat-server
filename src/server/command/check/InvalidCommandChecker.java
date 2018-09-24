package server.command.check;

import server.chat.ChatRoom;
import server.command.ChatCommand;
import server.command.ChatCommandHandler;
import server.message.MessageSender;

import java.util.ArrayList;
import java.util.Arrays;

public class InvalidCommandChecker extends ChatCommandHandler {
    private final ArrayList<String> validCommands;

    public InvalidCommandChecker(ChatRoom chatRoom, MessageSender messageSender) {
        super(chatRoom, messageSender);
        this.validCommands = new ArrayList<>(Arrays.asList("ENTRAR", "MSG", "SAIR"));
    }

    @Override
    public void handle(ChatCommand command) {
        boolean enterWithoutNickname;
        boolean messageWithoutContent;
        boolean commandExists;

        commandExists = validCommands.contains(command.getType());
        enterWithoutNickname = command.getType().equals("ENTRAR") && command.getParameter().equals("");
        messageWithoutContent = command.getType().equals("MSG") && command.getParameter().equals("");

        if (!commandExists || enterWithoutNickname || messageWithoutContent) {
            this.messageSender.sendErrorMessage(command.getSenderId(), "Comando inválido");
            return;
        }

        this.executeNext(command);
    }
}