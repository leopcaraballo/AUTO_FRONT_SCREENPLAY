package co.com.sofka.stepdefinitions;

import co.com.sofka.config.AutomationEnvironment;
import co.com.sofka.config.AutomationEnvironment.Credentials;
import co.com.sofka.questions.TrajectoryConsoleVisible;
import co.com.sofka.questions.TrajectorySearchResult;
import co.com.sofka.tasks.Login;
import co.com.sofka.tasks.OpenTrajectoryPage;
import co.com.sofka.tasks.SearchTrajectory;
import co.com.sofka.tasks.WaitForTrajectorySearchResponse;
import co.com.sofka.userinterface.TrajectoryPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Visibility;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.UUID;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

public class TrajectoryStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario está en la página de trayectorias")
    public void queElUsuarioEstaEnLaPaginaDeTrayectorias() {
        Credentials creds = AutomationEnvironment.validCredentials(environmentVariables);
        String loginUrl = AutomationEnvironment.loginUrl(environmentVariables);
        String trajectoryUrl = AutomationEnvironment.trajectoryUrl(environmentVariables);

        theActorCalled("Supervisor").attemptsTo(
            Login.withCredentials(loginUrl, creds.username(), creds.password()),
            OpenTrajectoryPage.at(trajectoryUrl)
        );
    }

    @When("la consola de trayectorias carga")
    public void laConsolaDeTrayectoriasCarga() {
        theActorInTheSpotlight().should(
            seeThat("La consola de trayectorias terminó de cargar",
                TrajectoryConsoleVisible.isDisplayed(), is(true))
        );
    }

    @Then("debería ver la sección de consulta de trayectorias")
    public void deberiaVerLaSeccionDeConsultaDeTrayectorias() {
        theActorInTheSpotlight().should(
            seeThat("La sección de consulta es visible",
                TrajectoryConsoleVisible.isDisplayed(), is(true))
        );
    }

    @And("debería ver los badges de estado en la página")
    public void deberiaVerLosBadgesDeEstadoEnLaPagina() {
        theActorInTheSpotlight().should(
            seeThat("Los badges de estado son visibles",
                Visibility.of(TrajectoryPage.LBL_STATUS_BADGE), is(true))
        );
    }

    @When("busca la trayectoria de un paciente inexistente")
    public void buscaLaTrayectoriaDeUnPacienteInexistente() {
        String fakePatientId = "NONEXISTENT-" + UUID.randomUUID().toString().substring(0, 8);
        theActorInTheSpotlight().attemptsTo(
            SearchTrajectory.forPatient(fakePatientId),
            WaitForTrajectorySearchResponse.emptyState()
        );
    }

    @When("busca la trayectoria con patientId {string}")
    public void buscaLaTrayectoriaConPatientId(String patientId) {
        // DDT — boundary value parametrizado desde Scenario Outline
        theActorInTheSpotlight().attemptsTo(
            SearchTrajectory.forPatient(patientId),
            WaitForTrajectorySearchResponse.emptyState()
        );
    }

    @Then("debería ver que no se encontraron resultados de trayectoria")
    public void deberiaVerQueNoSeEncontraronResultadosDeTrayectoria() {
        theActorInTheSpotlight().should(
            seeThat("No se encontraron resultados",
                TrajectorySearchResult.isEmpty(), is(true))
        );
    }
}
