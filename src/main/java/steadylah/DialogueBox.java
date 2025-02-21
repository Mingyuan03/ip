package steadylah;

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

import java.io.IOException;
import java.util.Collections;

public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayImage;

    private String dialogueString; // Separate out initialize() method for better structuring.
    private Image image; // Separate out initialize() method for better structuring.

    private DialogueBox(String newDialogue, Image newImage) {
        this.dialogueString = newDialogue;
        this.image = newImage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.initialize();
        } catch(IOException e) {
            System.err.println("IOException in FXMLLoader loading in DialogueBox: " + e);
        }
    }

    @FXML
    public void initialize() {
        this.dialogue.setText(this.dialogueString);
        this.displayImage.setImage(this.image);
    }

    @FXML
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> dialogueImageNodes = FXCollections.observableArrayList();
        Collections.reverse(dialogueImageNodes);
        this.getChildren().setAll(dialogueImageNodes);
    }

    public static DialogueBox showUserDialogueBox(String newDialogue, Image newImage) {
        return new DialogueBox(newDialogue, newImage);
    }

    public static DialogueBox showSteadyLahDialogueBox(String newDialogue, Image newImage) {
        DialogueBox dialogueBox = new DialogueBox(newDialogue, newImage);
        dialogueBox.flip();
        return dialogueBox;
    }
}
