package ru.starokozhev.SocialManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.starokozhev.SocialManager.service.bot.SocialManagerBot;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties
@EnableAutoConfiguration
public class SocialManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialManagerApplication.class, args);
	}

	@PostConstruct
	public void init() {
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new SocialManagerBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
