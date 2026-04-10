package co.com.sofka.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationPage {
    public static final Target TXT_PATIENT_ID = Target.the("Patient ID (NUIP) field")
            .located(By.name("patientId"));

    public static final Target TXT_PATIENT_NAME = Target.the("Patient Name field")
            .located(By.name("patientName"));

    public static final Target BTN_REGISTER = Target.the("Register Arrival button")
            .located(By.cssSelector("button.primary-button"));

    public static final Target LBL_MESSAGE_SUCCESS = Target.the("Success message")
            .located(By.cssSelector(".response-card--success"));

    public static final Target LBL_MESSAGE_ERROR = Target.the("Error message")
            .located(By.cssSelector(".form-field__error, .response-card--error"));
}
