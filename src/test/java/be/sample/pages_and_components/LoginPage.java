package be.sample.pages_and_components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Scope("prototype")
public class LoginPage extends AbstractPage<WebElement> {

    public void setUsername(String username) {
        set(By.id(""), username);
    }

    public void setPassword(String password) {
        set(By.id(""), password);
    }

    public void clickLogin() {

    }
}
