package com.benjamin.myBot;


import com.benjamin.myBot.service.ICommandHandler;
import com.benjamin.myBot.service.impl.CommandHandlerImpl;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class HelloBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    private final ICommandHandler commandHandler;

    public HelloBot (CommandHandlerImpl commandHandler){
        telegramClient = new OkHttpTelegramClient(getBotToken());
        this.commandHandler = commandHandler;
    }

    @Override
    public String getBotToken() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("BOT_TOKEN");
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String response_text = "";
            if(message.startsWith("/")){
                response_text = commandHandler.handle(message);
            }else{
                response_text = "I don't understand this command";
            }
            SendMessage response = SendMessage // Create a message object
                    .builder()
                    .chatId(chat_id)
                    .text(response_text)
                    .build();
            try {
                telegramClient.execute(response); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        System.out.println("Registered bot running state is: " + botSession.isRunning());
    }
}
