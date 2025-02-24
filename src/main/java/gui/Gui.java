package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yasumax.YasuMax;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Gui extends Application {
    private final YasuMax yasuMax = new YasuMax();

    /**
     * Render Scene and Stage for main window in GUI-mode.
     * @param stage Frame for JavaFX node container being AnchorPane to align UI parts with dynamically resized frame.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("YasuMax, your personal study companion!");
            fxmlLoader.<MainWindow>getController().setYasuMax(this.yasuMax);
            stage.show();
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }
}
