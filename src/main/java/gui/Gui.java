package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import steadylah.SteadyLah;

import java.io.IOException;

public class Gui extends Application {
    private final SteadyLah steadyLah = new SteadyLah();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSteadyLah(this.steadyLah);
            stage.show();
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }
}
