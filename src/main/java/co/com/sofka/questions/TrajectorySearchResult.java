package co.com.sofka.questions;

import co.com.sofka.userinterface.TrajectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TrajectorySearchResult implements Question<Boolean> {

    private final ResultExpectation expectation;

    public enum ResultExpectation {
        HAS_RESULTS,
        EMPTY,
        ANY
    }

    private TrajectorySearchResult(ResultExpectation expectation) {
        this.expectation = expectation;
    }

    public static TrajectorySearchResult hasResults() {
        return new TrajectorySearchResult(ResultExpectation.HAS_RESULTS);
    }

    public static TrajectorySearchResult isEmpty() {
        return new TrajectorySearchResult(ResultExpectation.EMPTY);
    }

    public static TrajectorySearchResult hasAnyState() {
        return new TrajectorySearchResult(ResultExpectation.ANY);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean hasResults = !TrajectoryPage.LBL_RESULTADO_ITEM.resolveAllFor(actor).isEmpty();
        boolean hasEmptyState = TrajectoryPage.LBL_SIN_RESULTADOS.resolveAllFor(actor).stream()
                .anyMatch(element -> element.isVisible() && element.getText().contains("No se encontraron"));

        if (expectation == ResultExpectation.HAS_RESULTS) {
            return hasResults;
        }

        if (expectation == ResultExpectation.EMPTY) {
            return hasEmptyState;
        }

        return hasResults || hasEmptyState;
    }
}
