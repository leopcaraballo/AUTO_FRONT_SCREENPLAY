package co.com.sofka.stepdefinitions;

import co.com.sofka.models.Patient;
import co.com.sofka.questions.RegistrationResult;
import co.com.sofka.tasks.FillRegistrationForm;
import co.com.sofka.tasks.OpenRegistrationPage;
import co.com.sofka.tasks.Login;
import co.com.sofka.tasks.SubmitRegistration;
import co.com.sofka.util.ExpectedResult;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

public class RegistrationStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("that the user is on the registration page")
    public void queElUsuarioEstaEnLaPaginaDeRegistro() {
        theActorCalled("Usuario").wasAbleTo(
                Login.asSuperAdmin(),
                OpenRegistrationPage.at()
        );
    }

    @Cuando("entering valid arrival data")
    public void ingresaDatosValidos() {
        String randomId = java.util.UUID.randomUUID().toString().substring(0, 8);
        Patient validPatient = new Patient(randomId, "Juan Perez Auto");
        theActorInTheSpotlight().attemptsTo(
                FillRegistrationForm.withData(validPatient),
                SubmitRegistration.form()
        );
    }

    @Entonces("should see a success message in the reception log")
    public void deberiaVerUnMensajeDeConfirmacion() {
        theActorInTheSpotlight().should(
                seeThat(RegistrationResult.is(ExpectedResult.SUCCESS), is(true))
        );
    }

    @Cuando("entering incomplete or invalid arrival data")
    public void ingresaDatosIncompletosOInvalidos() {
        Patient invalidPatient = new Patient("", "");
        theActorInTheSpotlight().attemptsTo(
                FillRegistrationForm.withData(invalidPatient),
                SubmitRegistration.form()
        );
    }

    @Entonces("should see validation error messages")
    public void deberiaVerMensajesDeError() {
        theActorInTheSpotlight().should(
                seeThat(RegistrationResult.is(ExpectedResult.FAILURE), is(true))
        );
    }
}
