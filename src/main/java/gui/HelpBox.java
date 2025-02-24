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
     * Instantiate new pop-up window to render custom help message, guiding users how to properly operate the GUI mode.
     * Initialised modality enforces explicit closing of this window before user can further interact with bot, thereby
     * ensuring user fully peruses help instructions.
     * @param helpMessage Bot's custom help message.
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
     * Programme this pop-up window to close only if user clicks on its exit button or right cross.
     */
    @FXML
    public void initialize() {
        this.exitButton.setOnAction(event -> this.close());
    }

    /**
     * Decouple help message's text initialisation from constructor to ensure it synchronously occurs after FXML load.
     * @param helpMessage Bot's custom help message.
     */
    private void setHelpMessage(String helpMessage) {
        this.helpMessage.setText(helpMessage);
    }
}
