package steadylah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {
    private final SteadyLah steadyLah = new SteadyLah();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane overallPane = fxmlLoader.load();
            Scene scene = new Scene(overallPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSteadyLah(this.steadyLah);
            stage.show();
        } catch (IOException e) {
            System.err.println("IOException in FXMLLoader loading in Gui: "+ e);
        }
    }
}
