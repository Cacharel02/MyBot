package com.benjamin.myBot.commands.impl;

import com.benjamin.myBot.commands.Command;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeCommand implements Command {
    @Override
    public String name() {
        return "/time";
    }

    @Override
    public String execute() {
        return "Il est actuellement " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH'h' mm'min' ss's'"));
    }
}
