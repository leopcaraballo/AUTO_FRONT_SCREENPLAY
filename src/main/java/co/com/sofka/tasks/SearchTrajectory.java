package co.com.sofka.tasks;

import co.com.sofka.userinterface.TrajectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class SearchTrajectory implements Task {

    private final String patientId;
    private final String queueId;

    public SearchTrajectory(String patientId, String queueId) {
        this.patientId = patientId;
        this.queueId = queueId;
    }

    public static SearchTrajectory forPatient(String patientId) {
        return Tasks.instrumented(SearchTrajectory.class, patientId, null);
    }

    public static SearchTrajectory forPatientInQueue(String patientId, String queueId) {
        return Tasks.instrumented(SearchTrajectory.class, patientId, queueId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(TrajectoryPage.TXT_PATIENT_ID, isClickable()).forNoMoreThan(10).seconds()
        );

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        setReactInputValue(js, "discoveryPatientId", patientId);

        if (queueId != null && !queueId.isBlank()) {
            setReactInputValue(js, "discoveryQueueId", queueId);
        }

        actor.attemptsTo(
            Click.on(TrajectoryPage.BTN_BUSCAR)
        );
    }

    private void setReactInputValue(JavascriptExecutor js, String elementId, String value) {
        js.executeScript(
            "var input = document.getElementById(arguments[0]);"
            + "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
            + "nativeInputValueSetter.call(input, arguments[1]);"
            + "input.dispatchEvent(new Event('input', { bubbles: true }));"
            + "input.dispatchEvent(new Event('change', { bubbles: true }));",
            elementId, value
        );
    }
}
