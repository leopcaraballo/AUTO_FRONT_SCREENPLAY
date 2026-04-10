package co.com.sofka.stepdefinitions;

import co.com.sofka.config.AutomationEnvironment;
import co.com.sofka.config.AutomationEnvironment.Credentials;
import co.com.sofka.models.Patient;
import co.com.sofka.questions.PublicLandingVisible;
import co.com.sofka.questions.RegistrationResult;
import co.com.sofka.tasks.FillRegistrationForm;
import co.com.sofka.tasks.Login;
import co.com.sofka.tasks.OpenPublicLanding;
import co.com.sofka.tasks.OpenRegistrationPage;
import co.com.sofka.tasks.SubmitRegistration;
import co.com.sofka.util.ExpectedResult;
import co.com.sofka.util.PatientFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

public class RegistrationStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    // --- Pantalla pública (Screenplay correcto — sin ChromeDriver manual) ---

    @Given("que un visitante abre la pantalla pública")
    public void visitanteAbrePantallaPublica() {
        // Arrange — OnlineCast maneja el WebDriver via Serenity
        String baseUrl = AutomationEnvironment.frontendBaseUrl(environmentVariables);

        // Act
        theActorCalled("Visitor").attemptsTo(
            OpenPublicLanding.at(baseUrl)
        );
    }

    @Then("el mensaje de bienvenida debería ser visible")
    public void mensajeDeBienvenidaDebeSerVisible() {
        // Assert
        theActorInTheSpotlight().should(
            seeThat("Mensaje de bienvenida visible", PublicLandingVisible.isVisible(), is(true))
        );
    }

    // --- Registro de paciente ---

    @Given("que el usuario está en la página de registro")
    public void queElUsuarioEstaEnLaPaginaDeRegistro() {
        // Arrange — OnlineCast asigna driver automáticamente
        Credentials validCredentials = AutomationEnvironment.validCredentials(environmentVariables);

        theActorCalled("Usuario").attemptsTo(
            Login.withCredentials(
                AutomationEnvironment.loginUrl(environmentVariables),
                validCredentials.username(),
                validCredentials.password()
            ),
            OpenRegistrationPage.at(AutomationEnvironment.receptionUrl(environmentVariables))
        );
    }

    @When("ingresa datos de llegada válidos")
    public void ingresaDatosDeLlegadaValidos() {
        // Arrange — TDT pattern
        Patient validPatient = PatientFactory.validArrival(environmentVariables);

        // Act
        theActorInTheSpotlight().attemptsTo(
            FillRegistrationForm.withData(validPatient),
            SubmitRegistration.form()
        );
    }

    @Then("debería ver un mensaje de éxito en el registro de recepción")
    public void deberiaVerUnMensajeDeExitoEnElRegistroDeRecepcion() {
        // Assert
        theActorInTheSpotlight().should(
            seeThat("Registro exitoso", RegistrationResult.is(ExpectedResult.SUCCESS), is(true))
        );
    }

    @When("ingresa datos de llegada incompletos o inválidos")
    public void ingresaDatosDeLlegadaIncompletosOInvalidos() {
        // Arrange — TDT pattern: missing required field
        Patient invalidPatient = PatientFactory.missingRequiredPatientId(environmentVariables);

        // Act
        theActorInTheSpotlight().attemptsTo(
            FillRegistrationForm.withData(invalidPatient),
            SubmitRegistration.form()
        );
    }

    @Then("debería ver mensajes de error de validación")
    public void deberiaVerMensajesDeErrorDeValidacion() {
        // Assert
        theActorInTheSpotlight().should(
            seeThat("Error de validación visible", RegistrationResult.is(ExpectedResult.FAILURE), is(true))
        );
    }
}
