package co.com.sofka.tasks;

import co.com.sofka.userinterface.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {
    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().thePageNamed("login"),
                Enter.theValue(username).into(LoginPage.TXT_USERNAME),
                Enter.theValue(password).into(LoginPage.TXT_PASSWORD),
                Click.on(LoginPage.BTN_LOGIN)
        );
    }

    public static Login withCredentials(String username, String password) {
        return instrumented(Login.class, username, password);
    }

    public static Login asSuperAdmin() {
        return instrumented(Login.class, "superadmin", "SuperAdmin@2026Dev!");
    }
}
