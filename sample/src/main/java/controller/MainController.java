package controller;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import models.AudioModel;
import models.CameraModel;
import models.LocationModel;
import view.MainView;

public class MainController {
    private final AudioModel audioModel;
    private final CameraModel cameraModel;
    private final LocationModel locationModel;
    private final MainView view;

    public MainController(MainView view) {
        this.audioModel = new AudioModel();
        this.cameraModel = new CameraModel(Webcam.getDefault());
        this.locationModel = new LocationModel();
        this.view = view;

        initializeEventHandlers();
    }

    private void initializeEventHandlers() {
        view.setRecordButtonAction(event -> {
            try {
                if (!audioModel.isRecording()) {
                    audioModel.startRecording();
                    view.setRecordButtonText("Enregistrer Audio");
                } else {
                    audioModel.stopRecording();
                    view.setRecordButtonText("Arrêter Enregistrement");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.setCameraButtonAction(event -> {
            try {
                cameraModel.capturePhoto();
                System.out.println("Photo enregistrée sous capture.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.setLocationButtonAction(event -> {
            new Thread(() -> {
                try {
                    String location = locationModel.getLocation();
                    Platform.runLater(() -> System.out.println(location));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    public void shutdown() {
        cameraModel.close();
    }
}