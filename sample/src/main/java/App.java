import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.MainController;
import view.MainView;

/**
 * JavaFX App
 */
public class App extends Application {
    private MainController controller;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        MainView view = new MainView();
        controller = new MainController(view);

        Scene scene = new Scene(view, 300, 200);
        stage.setTitle("Application Desktop");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        if (controller != null) {
            controller.shutdown();
        }
    }
}