package co.com.sofka.config;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.model.environment.UndefinedEnvironmentVariableException;
import net.thucydides.model.util.EnvironmentVariables;

public final class AutomationEnvironment {

    private static final String DEFAULT_FRONTEND_BASE_URL = "http://localhost:3000";
    private static final String DEFAULT_INVALID_USERNAME = "usuario_incorrecto";
    private static final String DEFAULT_INVALID_PASSWORD = "password_incorrecto";
    private static final String DEFAULT_VALID_PATIENT_NAME = "Juan Perez Auto";
    private static final String DEFAULT_INVALID_PATIENT_NAME = "Ana Perez";

    private AutomationEnvironment() {
    }

    public static String frontendBaseUrl(EnvironmentVariables environmentVariables) {
        return normalizeBaseUrl(optional(environmentVariables, "frontend.base.url", "RLAPP_FRONTEND_BASE_URL", DEFAULT_FRONTEND_BASE_URL));
    }

    public static Credentials validCredentials(EnvironmentVariables environmentVariables) {
        return new Credentials(
                required(environmentVariables, "credentials.valid.username", "RLAPP_VALID_USERNAME"),
                required(environmentVariables, "credentials.valid.password", "RLAPP_VALID_PASSWORD")
        );
    }

    public static Credentials invalidCredentials(EnvironmentVariables environmentVariables) {
        return new Credentials(
                optional(environmentVariables, "credentials.invalid.username", "RLAPP_INVALID_USERNAME", DEFAULT_INVALID_USERNAME),
                optional(environmentVariables, "credentials.invalid.password", "RLAPP_INVALID_PASSWORD", DEFAULT_INVALID_PASSWORD)
        );
    }

    public static String loginUrl(EnvironmentVariables environmentVariables) {
        String loginPath = optional(environmentVariables, "frontend.login.path", "RLAPP_LOGIN_PATH", "/login");
        return normalizeBaseUrl(optional(environmentVariables, "frontend.base.url", "RLAPP_FRONTEND_BASE_URL", DEFAULT_FRONTEND_BASE_URL))
                + normalizePath(loginPath);
    }

    public static String receptionUrl(EnvironmentVariables environmentVariables) {
        String receptionPath = optional(environmentVariables, "frontend.reception.path", "RLAPP_RECEPTION_PATH", "/reception");
        return normalizeBaseUrl(optional(environmentVariables, "frontend.base.url", "RLAPP_FRONTEND_BASE_URL", DEFAULT_FRONTEND_BASE_URL))
                + normalizePath(receptionPath);
    }

    public static String trajectoryUrl(EnvironmentVariables environmentVariables) {
        String trajectoryPath = optional(environmentVariables, "frontend.trajectory.path", "RLAPP_TRAJECTORY_PATH", "/trajectory");
        return normalizeBaseUrl(optional(environmentVariables, "frontend.base.url", "RLAPP_FRONTEND_BASE_URL", DEFAULT_FRONTEND_BASE_URL))
                + normalizePath(trajectoryPath);
    }

    public static String validPatientName(EnvironmentVariables environmentVariables) {
        return optional(environmentVariables, "testdata.patient.valid.name", "RLAPP_VALID_PATIENT_NAME", DEFAULT_VALID_PATIENT_NAME);
    }

    public static String invalidPatientName(EnvironmentVariables environmentVariables) {
        return optional(environmentVariables, "testdata.patient.invalid.name", "RLAPP_INVALID_PATIENT_NAME", DEFAULT_INVALID_PATIENT_NAME);
    }

    private static String required(
            EnvironmentVariables environmentVariables,
            String propertyName,
            String environmentVariable
    ) {
        String value = preferredValue(environmentVariables, propertyName, environmentVariable);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                "Missing required automation property: " + propertyName
                    + ". Configure system property '" + propertyName + "' or environment variable '" + environmentVariable + "'."
            );
        }

        return value;
    }

    private static String optional(
            EnvironmentVariables environmentVariables,
            String propertyName,
            String environmentVariable,
            String defaultValue
    ) {
        String value = preferredValue(environmentVariables, propertyName, environmentVariable);
        return value == null || value.isBlank() ? defaultValue : value;
    }

    private static String preferredValue(
            EnvironmentVariables environmentVariables,
            String propertyName,
            String environmentVariable
    ) {
        String systemPropertyValue = System.getProperty(propertyName);
        if (systemPropertyValue != null && !systemPropertyValue.isBlank()) {
            return systemPropertyValue;
        }

        String environmentValue = System.getenv(environmentVariable);
        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue;
        }

        try {
            return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(propertyName);
        } catch (UndefinedEnvironmentVariableException e) {
            return null;
        }
    }

    private static String normalizeBaseUrl(String baseUrl) {
        return baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }

    private static String normalizePath(String path) {
        return path.startsWith("/") ? path : "/" + path;
    }

    public record Credentials(String username, String password) {
    }
}
