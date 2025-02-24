package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class HelpBox extends Stage {
    @FXML
    private Label helpMessage;
    @FXML
    private Button exitButton;

    /**
     * Instantiate pop-up window to render custom help message, guiding users how to properly operate app in GUI mode,
     * while guaranteeing user must fully understand and explicitly close the window before further dialogues can ensue.
     * @param helpMessage custom steadyLah bot help message.
     */
    public HelpBox(String helpMessage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpBox.fxml"));
            assert fxmlLoader.getLocation() != null : "HelpBox.fxml absent in ../resources/view";
            fxmlLoader.setController(this);
            VBox root = fxmlLoader.load(); // Automatically set controller specified in HelpBox.fxml
            this.setScene(new Scene(root));
            this.initModality(Modality.APPLICATION_MODAL);
            this.setTitle("Help Box");
            this.setResizable(true);
            fxmlLoader.<HelpBox>getController().setHelpMessage(helpMessage);
        } catch (IOException e) {
            System.err.println("IOException in FXMLLoader loading in DialogueBox: " + e);
        }
    }

    /**
     * Program lazily exitButton to correctly close the whole window on being clicked explicitly vs hovering/automatic.
     */
    @FXML
    public void initialize() {
        this.exitButton.setOnAction(event -> this.close());
    }

    /**
     * Render custom steadyLah bot help message in window as a String text in VBox-typed pop-up window instance.
     * @param helpMessage custom steadyLah bot help message.
     */
    private void setHelpMessage(String helpMessage) {
        this.helpMessage.setText(helpMessage);
    }
}
