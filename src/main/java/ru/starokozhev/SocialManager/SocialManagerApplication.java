package ru.starokozhev.SocialManager;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.starokozhev.SocialManager.service.bot.SocialManagerBot;
import ru.starokozhev.SocialManager.service.selenium.SeleniumService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties
@EnableAutoConfiguration
@RequiredArgsConstructor
public class SocialManagerApplication {

	private final SeleniumService seleniumService;

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

	@PreDestroy
	public void clearResources() {
		SeleniumService.stopService();

		if (seleniumService != null)
			seleniumService.quitDriver();
	}

	@Bean
	public RestTemplate restTemplate() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder
				.create()
				.setRedirectStrategy(new DefaultRedirectStrategy());

		CloseableHttpClient httpClient = httpClientBuilder.build();

		var requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		//requestFactory.setConnectTimeout(30000);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory);

		return restTemplate;
	}

}
