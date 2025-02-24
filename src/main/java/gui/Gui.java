package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shinpaimax.ShinpaiMax;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Gui extends Application {
    private final ShinpaiMax shinpaiMax = new ShinpaiMax();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ShinpaiMax, your personal study companion!");
            fxmlLoader.<MainWindow>getController().setShinpaiMax(this.shinpaiMax);
            stage.show();
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }
}
