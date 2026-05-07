package com.benjamin.myBot.service.impl;

import com.benjamin.myBot.commands.Command;
import com.benjamin.myBot.service.ICommandHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommandHandlerImpl implements ICommandHandler {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandHandlerImpl(List<Command> commandList) {

        for (Command command : commandList) {
            commands.put(command.name(), command);
        }
    }

    @Override
    public SendMessage handle(Update update) {
        String command = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        if(command.startsWith("/")){
            String commandKey = (!command.contains(" "))?command:command.split(" ")[0];
            if(commands.containsKey(commandKey)) {
                if(!command.contains(" ")){
                    return buildSendMessage(chat_id, commands.get(commandKey).execute());
                }
                String[] args = command.substring(command.indexOf(" ")+1).split(" ");
                return buildSendMessage(chat_id, commands.get(commandKey).execute(args));
            }
            return buildSendMessage(chat_id,"Commande non prise en compte");
        }
        return  buildSendMessage(chat_id, "I don't understand this command");

    }

    private SendMessage buildSendMessage(long chat_id, String text){
        return SendMessage.builder()
                .chatId(chat_id)
                .text(text)
                .build();
    }
}
