package ru.starokozhev.SocialManager.service.mail;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import ru.starokozhev.SocialManager.dto.TemporaryMailWrapper;

import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Component
@Log4j2
public class TemporaryMailService {

    //TODO use @Value
    private final static String TEMPORARY_MAIL_SOURCE = "https://dropmail.me/ru/";

    public TemporaryMailWrapper getTemporaryMail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {
            driver.get(TEMPORARY_MAIL_SOURCE);
            String mailResult = wait.until(presenceOfElementLocated(By.cssSelector("h2 > span.email"))).getText();

            String scriptToOpenNewTab = "window.open('about:blank','_blank');";
            ((JavascriptExecutor) driver).executeScript(scriptToOpenNewTab);

            ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allTabs.get(allTabs.size() - 1));

            return new TemporaryMailWrapper(mailResult);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean successRegister(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        String mailResult = wait.until(presenceOfElementLocated(By.cssSelector("ul.list-unstyled.messages-list > li > div > pre"))).getText();

        int x = 4;

        return true;
    }

}