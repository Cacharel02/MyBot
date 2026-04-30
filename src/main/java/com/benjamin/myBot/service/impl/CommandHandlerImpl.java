package com.benjamin.myBot.service.impl;

import com.benjamin.myBot.service.ICommandHandler;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommandHandlerImpl implements ICommandHandler {

    @Override
    public String handle(String command) {
        if(command.equals("/heure")) {
            return "Il est actuellement " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH'h' mm'min' ss's'"));
        }
        return "Commande non prise en compte";
    }
}
