package co.com.sofka.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TrajectoryPage {

    public static final Target TXT_PATIENT_ID = Target.the("Patient ID search field")
            .located(By.id("discoveryPatientId"));

    public static final Target TXT_QUEUE_ID = Target.the("Queue ID search field")
            .located(By.id("discoveryQueueId"));

    public static final Target BTN_BUSCAR = Target.the("Search trajectories button")
            .located(By.cssSelector(".operation-card form.form-grid button.primary-button"));

    public static final Target LBL_RESULTADO_ITEM = Target.the("Trajectory result item")
            .located(By.cssSelector(".history-list .history-item"));

    public static final Target BTN_CARGAR_TRAYECTORIA = Target.the("Load trajectory button")
            .located(By.cssSelector(".history-item button.ghost-button"));

    public static final Target LBL_SIN_RESULTADOS = Target.the("No results message")
            .located(By.cssSelector(".response-card__title"));

    public static final Target LBL_DETALLE_EYEBROW = Target.the("Trajectory detail eyebrow")
            .located(By.xpath("//*[contains(@class, 'panel__eyebrow') and contains(text(), 'Lectura persistida')]"));

    public static final Target LBL_STAGE_ITEM = Target.the("Trajectory stage item")
            .located(By.cssSelector(".panel .history-list .history-item h3"));

    public static final Target LBL_STATUS_BADGE = Target.the("Status badge")
            .located(By.cssSelector(".status-badge"));

    public static final Target LBL_SECCION_CONSULTA = Target.the("Consultation section")
            .located(By.id("discoveryPatientId"));
}
