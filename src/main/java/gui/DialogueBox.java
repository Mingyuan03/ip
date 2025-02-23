package gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayImage;

    private final String dialogueString; // Separate out initialize() method for better structuring.
    private final Image image; // Separate out initialize() method for better structuring.

    private DialogueBox(String newDialogue, Image newImage) {
        this.dialogueString = newDialogue;
        this.image = newImage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.initialize();
        } catch (IOException e) {
            System.err.println("IOException in FXMLLoader loading in DialogueBox: " + e);
        }
    }

    /**
     * Set dialogueBox text and image automatically after FXML file loading and injection of UI components.
     * <p> Add assertions to guarantee both dialogue text and image view are initialised before this call. </p>
     */
    @FXML
    public void initialize() {
        assert this.dialogue != null;
        this.dialogue.setText(this.dialogueString);
        assert this.displayImage != null;
        this.displayImage.setImage(this.image);
    }

    /**
     * Reflect dialogueBox and image order, and realign UI components to opposite horizontal end of scrollPane for
     * user dialogueBox vs steadyLah bot dialogueBox.
     */
    @FXML
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        this.dialogue.getStyleClass().add("reply-label");
        ObservableList<Node> dialogueImageNodes = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(dialogueImageNodes);
        this.getChildren().setAll(dialogueImageNodes);

    }

    /**
     * Instantiate new user dialogueBox, better encapsulating private constructor for MainWindow.java.
     * @param newDialogue Custom user descriptionString to render in GUI mode.
     * @param newImage User image.
     * @return user dialogueBox.
     */
    public static DialogueBox showUserDialogueBox(String newDialogue, Image newImage) {
        return new DialogueBox(newDialogue, newImage);
    }

    /**
     * Instantiate new steadyLah bot dialogueBox, better encapsulating private constructor for MainWindow.java.
     * @param newDialogue Custom corresponding steadyLah bot response to preceding user descriptionString.
     * @param newImage steadyLah bot image.
     * @return steadyLah bot dialogueBox.
     */
    public static DialogueBox showSteadyLahDialogueBox(String newDialogue, Image newImage) {
        DialogueBox dialogueBox = new DialogueBox(newDialogue, newImage);
        dialogueBox.flip();
        return dialogueBox;
    }
}
