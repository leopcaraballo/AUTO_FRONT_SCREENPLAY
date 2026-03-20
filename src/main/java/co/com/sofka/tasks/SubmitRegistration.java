package co.com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.sofka.userinterface.RegistrationPage.BTN_REGISTER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SubmitRegistration implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_REGISTER)
        );
    }

    public static SubmitRegistration form() {
        return instrumented(SubmitRegistration.class);
    }
}
