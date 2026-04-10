package co.com.sofka.tasks;

import co.com.sofka.userinterface.LoginPage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {
    private final String loginUrl;
    private final String username;
    private final String password;

    public Login(String loginUrl, String username, String password) {
        this.loginUrl = loginUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(loginUrl);
        LoginPage.TXT_USERNAME.resolveFor(actor).waitUntilVisible().clear();
        LoginPage.TXT_USERNAME.resolveFor(actor).type(username);
        LoginPage.TXT_PASSWORD.resolveFor(actor).waitUntilVisible().clear();
        LoginPage.TXT_PASSWORD.resolveFor(actor).type(password);
        LoginPage.BTN_LOGIN.resolveFor(actor).waitUntilClickable().click();
        new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(10))
                .until(driver -> !driver.getCurrentUrl().startsWith(loginUrl));
    }

    public static Login withCredentials(String loginUrl, String username, String password) {
        return instrumented(Login.class, loginUrl, username, password);
    }
}
