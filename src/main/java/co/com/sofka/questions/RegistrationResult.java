package co.com.sofka.questions;

import co.com.sofka.util.ExpectedResult;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

import static co.com.sofka.userinterface.RegistrationPage.LBL_MESSAGE_SUCCESS;
import static co.com.sofka.userinterface.RegistrationPage.LBL_MESSAGE_ERROR;

public class RegistrationResult implements Question<Boolean> {

    private final ExpectedResult expected;

    public RegistrationResult(ExpectedResult expected) {
        this.expected = expected;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (expected == ExpectedResult.SUCCESS) {
            return Visibility.of(LBL_MESSAGE_SUCCESS).answeredBy(actor);
        } else {
            return Visibility.of(LBL_MESSAGE_ERROR).answeredBy(actor);
        }
    }

    public static RegistrationResult is(ExpectedResult expected) {
        return new RegistrationResult(expected);
    }
}
