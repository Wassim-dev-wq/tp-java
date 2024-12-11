import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import view.MainView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class GUITests {

    private MainView view;

    @Start
    void start(Stage stage) {
        view = new MainView();
        stage.setScene(new Scene(view));
        stage.show();
    }

    @Test
    void testButtonsExist() {
        assertNotNull(view.getRecordButton(), "Record button should not be null");
        assertNotNull(view.getCameraButton(), "Camera button should not be null");
        assertNotNull(view.getLocationButton(), "Location button should not be null");
    }

    @Test
    void testButtonActions() {
        // Configuration d'une action test
        EventHandler<ActionEvent> testHandler = e -> System.out.println("Test action");

        // Configure les actions
        view.setRecordButtonAction(testHandler);
        view.setCameraButtonAction(testHandler);
        view.setLocationButtonAction(testHandler);

        // Vérifie que les actions sont configurées
        assertEquals(testHandler, view.getRecordButton().getOnAction(), "Record button action should be set");
        assertEquals(testHandler, view.getCameraButton().getOnAction(), "Camera button action should be set");
        assertEquals(testHandler, view.getLocationButton().getOnAction(), "Location button action should be set");
    }
}