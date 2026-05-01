package com.benjamin.myBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ICommandHandler {

    SendMessage handle(Update update);
}
