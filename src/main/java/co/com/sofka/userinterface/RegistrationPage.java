package co.com.sofka.userinterface;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("http://localhost:3000/reception")
public class RegistrationPage {
    public static final Target TXT_PATIENT_ID = Target.the("Patient ID (NUIP) field")
            .located(By.name("patientId"));
    
    public static final Target TXT_PATIENT_NAME = Target.the("Patient Name field")
            .located(By.name("patientName"));
    
    public static final Target BTN_REGISTER = Target.the("Register Arrival button")
            .located(By.xpath("//button[contains(text(),'Register arrival')]"));
    
    public static final Target LBL_MESSAGE_SUCCESS = Target.the("Success message")
            .located(By.xpath("//*[contains(text(),'successfully')]"));
    
    public static final Target LBL_MESSAGE_ERROR = Target.the("Error message")
            .located(By.xpath("//*[contains(@class,'error') or contains(text(),'required')]"));
}
