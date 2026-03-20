package co.com.sofka.tasks;

import co.com.sofka.models.Patient;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.sofka.userinterface.RegistrationPage.TXT_PATIENT_ID;
import static co.com.sofka.userinterface.RegistrationPage.TXT_PATIENT_NAME;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillRegistrationForm implements Task {

    private final Patient patient;

    public FillRegistrationForm(Patient patient) {
        this.patient = patient;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(patient.getId()).into(TXT_PATIENT_ID),
                Enter.theValue(patient.getName()).into(TXT_PATIENT_NAME)
        );
    }

    public static FillRegistrationForm withData(Patient patient) {
        return instrumented(FillRegistrationForm.class, patient);
    }
}
