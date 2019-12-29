package ru.starokozhev.SocialManager.service.registrator;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.starokozhev.SocialManager.dto.TemporaryMailWrapper;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

@Component
@Log4j2
public class InstagramService {

    private final static String INSTAGRAM_SOURCE = "https://www.instagram.com/";
    //private WebDriver driver;
    private BCryptPasswordEncoder passwordEncoder;
    //private ProxyConfigService proxyConfigService;

    @Autowired
    public InstagramService(BCryptPasswordEncoder passwordEncoder) {
        //this.driver = webDriver;
        this.passwordEncoder = passwordEncoder;
        //this.proxyConfigService = proxyConfigService;
    }

    @SneakyThrows
    public Account registerAccount(TemporaryMailWrapper wrapper, WebDriver driver) {
        //Proxy proxy = proxyConfigService.configureCurrentProxy("test", "test", "test", "test");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        Account account = new Account();

        try {
            driver.get(INSTAGRAM_SOURCE);

            account.setLogin(wrapper.getMail());
            wait.until(presenceOfElementLocated(By.name("emailOrPhone"))).sendKeys(account.getLogin());

            account.setFullName(generateFullName());
            wait.until(presenceOfElementLocated(By.name("fullName"))).sendKeys(account.getFullName());

            account.setUserName(generateUsername(wrapper.getMail()));
            wait.until(presenceOfElementLocated(By.name("username"))).sendKeys(account.getUserName());

            account.setPassword(passwordEncoder.encode(wrapper.getMail()));
            wait.until(presenceOfElementLocated(By.name("password"))).sendKeys(account.getPassword());

            List<WebElement> buttons = wait.until(visibilityOfAllElementsLocatedBy(By.tagName("button")));

            for (WebElement element : buttons) {
                if (element.getAttribute("type").equals("submit")) {
                    element.click();
                    break;
                }
            }

            //WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h2 > span.email")));

            synchronized (driver) {
                driver.wait(10_000);
            }

            ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allTabs.get(0));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return account;
    }

    private String generateFullName() {
        List<String> surnames = Arrays.asList("Русый", "Чёрный", "Белый", "Серый", "Рыжий", "Петров", "Иванов", "Козлов");
        int surnameNumber = Math.toIntExact(Math.round(Math.random() * (surnames.size() - 1)));

        List<String> names = Arrays.asList("Пират", "Боливар", "Кристо", "Майк", "Джек", "Тонни", "Люци", "Грин", "Люк");
        int nameNumber = Math.toIntExact(Math.round(Math.random() * (names.size() - 1)));

        return names.get(nameNumber).concat(" ").concat(surnames.get(surnameNumber));
    }

    private String generateUsername(String mail) {
        long uniqueNumber = Math.round(Math.random() * 1_000);
        String resultUsername = mail.substring(0, mail.indexOf('@'));
        return resultUsername.concat(String.valueOf(uniqueNumber)).concat(resultUsername);
    }

}
