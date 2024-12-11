import controller.MainController;
import org.junit.jupiter.api.Test;
import view.MainView;

import static org.mockito.Mockito.*;

class IntegrationTests {

    @Test
    void testControllerWithServices() {
        MainView view = mock(MainView.class);
        MainController controller = new MainController(view);
        verify(view).setRecordButtonAction(any());
        verify(view).setCameraButtonAction(any());
        verify(view).setLocationButtonAction(any());
    }

    @Test
    void testControllerShutdown() {
        MainView view = mock(MainView.class);
        MainController controller = new MainController(view);

        controller.shutdown();
        // La fermeture se passe sans erreur
    }
}