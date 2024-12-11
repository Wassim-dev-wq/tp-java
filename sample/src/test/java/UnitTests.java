import com.github.sarxos.webcam.Webcam;
import models.AudioModel;
import models.CameraModel;
import models.LocationModel;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UnitTests {

    @Test
    void testAudioStartStop() {
        AudioModel audio = new AudioModel();
        assertFalse(audio.isRecording());

        try {
            audio.startRecording();
            assertTrue(audio.isRecording());

            audio.stopRecording();
            assertFalse(audio.isRecording());
        } catch (Exception e) {
            // Skip si pas de matériel audio
        }
    }

    @Test
    void testCameraCapture() throws Exception {
        Webcam mockWebcam = mock(Webcam.class);
        BufferedImage mockImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        when(mockWebcam.getImage()).thenReturn(mockImage);

        CameraModel camera = new CameraModel(mockWebcam);
        camera.capturePhoto();

        verify(mockWebcam).open();
        verify(mockWebcam).getImage();
        verify(mockWebcam).close();
    }

    @Test
    void testGPSCoordinates() throws Exception {
        LocationModel gps = new LocationModel();
        String location = gps.getLocation();

        assertTrue(location.contains("Lat:"));
        assertTrue(location.contains("Lon:"));
    }
}