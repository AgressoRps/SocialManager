package ru.starokozhev.SocialManager.service.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.TemporaryMailWrapper;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Component
@Log4j2
public class TemporaryMailService {

    private WebDriver driver;

    @Autowired
    public TemporaryMailService(WebDriver driver) {
        this.driver = driver;
    }

    public TemporaryMailWrapper getTemporaryMail() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
            WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
            System.out.println(firstResult.getAttribute("textContent"));
        } catch (Exception ex){
            ex.printStackTrace();
        }/*finally {
            driver.quit();
        }*/

        return null;
    }

}
