package co.com.sofka.tasks;

import co.com.sofka.userinterface.RegistrationPage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenRegistrationPage implements Task {

    private final String receptionUrl;

    public OpenRegistrationPage(String receptionUrl) {
        this.receptionUrl = receptionUrl;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(receptionUrl);
        RegistrationPage.TXT_PATIENT_ID.resolveFor(actor).waitUntilVisible();
        RegistrationPage.TXT_PATIENT_NAME.resolveFor(actor).waitUntilVisible();
        RegistrationPage.BTN_REGISTER.resolveFor(actor).waitUntilClickable();
    }

    public static OpenRegistrationPage at(String receptionUrl) {
        return instrumented(OpenRegistrationPage.class, receptionUrl);
    }
}
