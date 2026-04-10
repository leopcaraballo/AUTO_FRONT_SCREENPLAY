package co.com.sofka.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PublicLandingPage {
    public static final Target WELCOME_MESSAGE = Target.the("Welcome message")
            .located(By.cssSelector(".welcome-message, .landing-title, h1"));
}
