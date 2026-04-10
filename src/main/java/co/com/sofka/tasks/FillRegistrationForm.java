package co.com.sofka.tasks;

import co.com.sofka.models.Patient;
import co.com.sofka.userinterface.RegistrationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillRegistrationForm implements Task {

    private final Patient patient;

    public FillRegistrationForm(Patient patient) {
        this.patient = patient;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement patientIdField = RegistrationPage.TXT_PATIENT_ID.resolveFor(actor).waitUntilVisible();
        WebElement patientNameField = RegistrationPage.TXT_PATIENT_NAME.resolveFor(actor).waitUntilVisible();
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        setReactInputValue(js, patientIdField, safeValue(patient.getId()));
        setReactInputValue(js, patientNameField, safeValue(patient.getName()));
    }

    public static FillRegistrationForm withData(Patient patient) {
        return instrumented(FillRegistrationForm.class, patient);
    }

    private String safeValue(String value) {
        return value == null ? "" : value;
    }

    private void setReactInputValue(JavascriptExecutor js, WebElement element, String value) {
        js.executeScript(
            "var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element, value
        );
    }
}
