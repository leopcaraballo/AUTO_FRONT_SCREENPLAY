package co.com.sofka.questions;

import co.com.sofka.userinterface.PublicLandingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class PublicLandingVisible implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(PublicLandingPage.WELCOME_MESSAGE).answeredBy(actor);
    }

    public static PublicLandingVisible isVisible() {
        return new PublicLandingVisible();
    }
}
