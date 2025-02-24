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

    private final String dialogueString;
    private final Image image;

    /**
     * Instantiate new speech bubble for either user's input or bot's output.
     * This common constructor for both user and bot speech bubble is encapsulated to limit speech bubble generation in
     * GUI-mode to solely user xor bot, thereby minimising misuse.
     * @param newDialogue Text String being either user's input or bot's output.
     * @param newImage Avatar image being either user's caricature or bot's caricature.
     */
    private DialogueBox(String newDialogue, Image newImage) {
        assert newDialogue != null : "newDialogue text cannot be null";
        this.dialogueString = newDialogue;
        assert newImage != null : "Image cannot be null";
        this.image = newImage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            assert fxmlLoader.getLocation() != null : "DialogueBox.fxml absent in ../resources/view";
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.initialize();
        } catch (IOException e) {
            System.err.println("IOException in FXMLLoader loading in DialogueBox: " + e);
        }
    }

    /**
     * Inject 2 local FXML UI components after instantiation, namely this::dialogue and this::displayImage.
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
     * Swap speech bubble and avatar's rendering order, and realign the speech bubble, for bot's output to left side.
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
     * Instantiate new speech bubble for user's input.
     * @param newDialogue User's text string.
     * @param newImage User's avatar.
     * @return User's speech bubble in GUI-mode.
     */
    public static DialogueBox showUserDialogueBox(String newDialogue, Image newImage) {
        return new DialogueBox(newDialogue, newImage);
    }

    /**
     * Instantiate new speech bubble for bot's output.
     * @param newDialogue Bot's text response.
     * @param newImage Bot's avatar.
     * @return Bot's speech bubble in GUI-mode.
     */
    public static DialogueBox showYasuMaxDialogueBox(String newDialogue, Image newImage) {
        DialogueBox dialogueBox = new DialogueBox(newDialogue, newImage);
        dialogueBox.flip();
        return dialogueBox;
    }
}
