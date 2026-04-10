package co.com.sofka.tasks;

import co.com.sofka.userinterface.RegistrationPage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SubmitRegistration implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        RegistrationPage.BTN_REGISTER.resolveFor(actor).waitUntilClickable().click();
        new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(10)).until(
                driver -> isVisible(actor, RegistrationPage.LBL_MESSAGE_SUCCESS)
                        || isVisible(actor, RegistrationPage.LBL_MESSAGE_ERROR)
        );
    }

    public static SubmitRegistration form() {
        return instrumented(SubmitRegistration.class);
    }

    private <T extends Actor> boolean isVisible(T actor, Target target) {
        try {
            return target.resolveFor(actor).isVisible();
        } catch (Exception exception) {
            return false;
        }
    }
}
