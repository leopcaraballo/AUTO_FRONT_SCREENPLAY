package co.com.sofka.tasks;

import co.com.sofka.userinterface.PublicLandingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class OpenPublicLanding implements Task {

    private final String baseUrl;

    public OpenPublicLanding(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static OpenPublicLanding at(String baseUrl) {
        return Tasks.instrumented(OpenPublicLanding.class, baseUrl);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(baseUrl);
        PublicLandingPage.WELCOME_MESSAGE.resolveFor(actor).waitUntilVisible();
    }
}
