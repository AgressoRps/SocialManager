package ru.starokozhev.SocialManager.service.selenium;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class SeleniumService {

    private static ChromeDriverService service;
    private WebDriver driver;

    @SneakyThrows
    public static void createAndStartService() {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    public static void stopService() {
        service.stop();
    }

    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new RemoteWebDriver(service.getUrl(), options);
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
