package com.example.Joke.service;

import com.example.Joke.Config.LupaConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Random;

@Slf4j
@Component
public class BotTg extends TelegramLongPollingBot {

    final LupaConfig config;
    final PupaServiceInterface pupaService;

    public BotTg(LupaConfig config, PupaServiceInterface pupaService){
        this.config = config;
        this.pupaService = pupaService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken(){
        return config.getToken();
    }

    public String GetRandomJoke(String jsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonString);
        Random random = new Random();
        int randomIndex = random.nextInt(jsonArray.length());
        JSONObject jsonObject = jsonArray.getJSONObject(randomIndex);
        String text = jsonObject.getString("text");

        return text;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            if ("/joke".equals(messageText)) {
                String joke = pupaService.getAllJokes().toString(); // Получаем шутку через PupaServiceInterface
                String random_joke = "Шутка не найдена";
                try {
                    random_joke = GetRandomJoke(joke);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                sendTextMessage(chatId, random_joke);

            }
        }
    }

    private void sendTextMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
