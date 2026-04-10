package co.com.sofka.tasks;

import co.com.sofka.userinterface.TrajectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class OpenTrajectoryPage implements Task {

    private final String trajectoryUrl;

    public OpenTrajectoryPage(String trajectoryUrl) {
        this.trajectoryUrl = trajectoryUrl;
    }

    public static OpenTrajectoryPage at(String trajectoryUrl) {
        return Tasks.instrumented(OpenTrajectoryPage.class, trajectoryUrl);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(trajectoryUrl);
        TrajectoryPage.LBL_SECCION_CONSULTA.resolveFor(actor).waitUntilVisible();
        TrajectoryPage.BTN_BUSCAR.resolveFor(actor).waitUntilClickable();
    }
}
