package com.example.Joke.Config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class LupaConfig {
    @Value("${telegramBotName}")
    String botName;
    @Value("${telegramBotToken}")
    String token;
}
