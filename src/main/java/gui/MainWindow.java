package gui;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shinpaimax.ShinpaiMax;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField searchBox;
    @FXML
    private Button searchButton;

    private final Image userImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/User.png")));
    private final Image shinpaiMaxImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/ShinpaiMax.png")));
    private ShinpaiMax shinpaiMax;

    /**
     * Bind scrollPane to dialogueContainer for scrolling beyond margins of scrollPane,
     * and program lazily searchButton to correctly execute on being clicked explicitly vs hovering/automatic.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogueContainer.heightProperty());
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #EF7C00, #003D7C);");
        this.searchButton.setOnAction(clickEvent -> handleUserInput());
    }

    /**
     * Instantiate ShinpaiMax object solely in GUI mode (identical to in CLI mode) and bundle load functionality.
     * @param shinpaiMax instance.
     */
    public void setShinpaiMax(ShinpaiMax shinpaiMax) {
        this.shinpaiMax = shinpaiMax;
        this.loadPreviousSession(); // Previous Session could be independently in CLI and/or GUI mode.
    }

    /**
     * Load from cache past data for persistence.
     */
    private void loadPreviousSession() {
        this.dialogueContainer.getChildren().add(
                DialogueBox.showShinpaiMaxDialogueBox(this.shinpaiMax.loadTasksFromCache(), this.shinpaiMaxImage)
        );
    }

    /**
     * Render each pair of custom user descriptionString and ShinpaiMax bot's response, alongside their images,
     * as a pair of dialogueBoxes simultaneously rendered correctly solely in GUI mode, while handling "bye" command
     * like CLI mode's ShinpaiMax::execute guard block.
     */
    @FXML
    public void handleUserInput() {
        String searchInput = this.searchBox.getText().trim();
        this.searchBox.clear();
        if (searchInput.equals("bye")) {
            this.dialogueContainer.getChildren().addAll(
                    DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                    DialogueBox.showShinpaiMaxDialogueBox(this.shinpaiMax.saveTasksToCache(), this.shinpaiMaxImage)
            );
            Platform.exit(); // Terminate on reaching exit command
            return;
        }
        String[] shinpaiMaxOutput = this.shinpaiMax.processCommandByMap(searchInput);
        assert shinpaiMaxOutput.length == 2
                : "Error in processing user input: shinpaimax bot must output only response and whether help is needed";
        if (shinpaiMaxOutput[1].equals("true")) {
            // this.dialogueContainer.getChildren().add(DialogueBox.showUserDialogueBox(searchInput, this.userImage));
            new HelpBox(shinpaiMaxOutput[0]).showAndWait();
            return;
        }
        this.dialogueContainer.getChildren().addAll(
                DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                DialogueBox.showShinpaiMaxDialogueBox(shinpaiMaxOutput[0], this.shinpaiMaxImage)
        );
    }
}
