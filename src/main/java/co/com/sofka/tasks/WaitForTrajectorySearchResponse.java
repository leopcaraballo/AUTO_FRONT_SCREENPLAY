package co.com.sofka.tasks;

import co.com.sofka.questions.TrajectorySearchResult.ResultExpectation;
import co.com.sofka.userinterface.TrajectoryPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitForTrajectorySearchResponse implements Task {

    private final ResultExpectation expectation;

    public WaitForTrajectorySearchResponse(ResultExpectation expectation) {
        this.expectation = expectation;
    }

    public static WaitForTrajectorySearchResponse emptyState() {
        return Tasks.instrumented(WaitForTrajectorySearchResponse.class, ResultExpectation.EMPTY);
    }

    public static WaitForTrajectorySearchResponse withResults() {
        return Tasks.instrumented(WaitForTrajectorySearchResponse.class, ResultExpectation.HAS_RESULTS);
    }

    public static WaitForTrajectorySearchResponse anyState() {
        return Tasks.instrumented(WaitForTrajectorySearchResponse.class, ResultExpectation.ANY);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriverWait wait = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(10));

        wait.until(driver -> {
            boolean hasResults = hasVisibleElements(TrajectoryPage.LBL_RESULTADO_ITEM.resolveAllFor(actor));
            boolean hasEmptyState = TrajectoryPage.LBL_SIN_RESULTADOS.resolveAllFor(actor).stream()
                    .anyMatch(element -> element.isVisible() && element.getText().contains("No se encontraron"));

            if (expectation == ResultExpectation.HAS_RESULTS) {
                return hasResults;
            }

            if (expectation == ResultExpectation.EMPTY) {
                return hasEmptyState;
            }

            return hasResults || hasEmptyState;
        });
    }

    private boolean hasVisibleElements(List<WebElementFacade> elements) {
        return elements.stream().anyMatch(WebElementFacade::isVisible);
    }
}
