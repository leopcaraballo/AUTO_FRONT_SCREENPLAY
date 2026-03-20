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

    @Dado("que el usuario está en la página de registro de llegada")
    public void queElUsuarioEstaEnLaPaginaDeRegistro() {
        theActorCalled("Usuario").wasAbleTo(
                Login.asSuperAdmin(),
                OpenRegistrationPage.at()
        );
    }

    @Cuando("ingresa datos de llegada válidos")
    public void ingresaDatosValidos() {
        Patient validPatient = new Patient("123456789", "Juan Perez");
        theActorInTheSpotlight().attemptsTo(
                FillRegistrationForm.withData(validPatient),
                SubmitRegistration.form()
        );
    }

    @Entonces("debería ver un mensaje de éxito en el diario de recepción")
    public void deberiaVerUnMensajeDeConfirmacion() {
        theActorInTheSpotlight().should(
                seeThat(RegistrationResult.is(ExpectedResult.SUCCESS), is(true))
        );
    }

    @Cuando("ingresa datos de llegada incompletos o inválidos")
    public void ingresaDatosIncompletosOInvalidos() {
        Patient invalidPatient = new Patient("", "");
        theActorInTheSpotlight().attemptsTo(
                FillRegistrationForm.withData(invalidPatient),
                SubmitRegistration.form()
        );
    }

    @Entonces("debería ver mensajes de error de validación")
    public void deberiaVerMensajesDeError() {
        theActorInTheSpotlight().should(
                seeThat(RegistrationResult.is(ExpectedResult.FAILURE), is(true))
        );
    }
}
