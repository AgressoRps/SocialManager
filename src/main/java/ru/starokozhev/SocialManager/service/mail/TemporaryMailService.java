package ru.starokozhev.SocialManager.service.mail;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import ru.starokozhev.SocialManager.dto.TemporaryMailWrapper;

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
            WebElement mailResult = wait.until(presenceOfElementLocated(By.cssSelector("h2 > span.email")));
            return new TemporaryMailWrapper(mailResult.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
