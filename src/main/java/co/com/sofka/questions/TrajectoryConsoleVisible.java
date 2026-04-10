package co.com.sofka.questions;

import co.com.sofka.userinterface.TrajectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class TrajectoryConsoleVisible implements Question<Boolean> {

    public static TrajectoryConsoleVisible isDisplayed() {
        return new TrajectoryConsoleVisible();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(TrajectoryPage.LBL_SECCION_CONSULTA).answeredBy(actor)
                && Visibility.of(TrajectoryPage.BTN_BUSCAR).answeredBy(actor);
    }
}
