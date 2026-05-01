package com.benjamin.myBot.service.impl;

import com.benjamin.myBot.service.ICommandHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommandHandlerImpl implements ICommandHandler {

    @Override
    public SendMessage handle(Update update) {
        String command = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        if(command.startsWith("/")){
            if(command.equals("/heure")) {
                return buildSendMessage(chat_id,"Il est actuellement " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH'h' mm'min' ss's'")));
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
