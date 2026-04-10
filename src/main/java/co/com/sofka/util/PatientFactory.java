package co.com.sofka.util;

import co.com.sofka.models.Patient;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.UUID;

import static co.com.sofka.config.AutomationEnvironment.invalidPatientName;
import static co.com.sofka.config.AutomationEnvironment.validPatientName;

public final class PatientFactory {

    private PatientFactory() {
    }

    public static Patient validArrival(EnvironmentVariables environmentVariables) {
        return new Patient(generatePatientId(), validPatientName(environmentVariables));
    }

    public static Patient missingRequiredPatientId(EnvironmentVariables environmentVariables) {
        return new Patient("", invalidPatientName(environmentVariables));
    }

    private static String generatePatientId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
