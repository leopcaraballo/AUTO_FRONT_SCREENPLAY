package co.com.sofka.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target TXT_USERNAME = Target.the("Username field")
            .located(By.id("identifier"));
    public static final Target TXT_PASSWORD = Target.the("Password field")
            .located(By.id("password"));
    public static final Target BTN_LOGIN = Target.the("Login button")
            .located(By.cssSelector("button.primary-button"));
}
