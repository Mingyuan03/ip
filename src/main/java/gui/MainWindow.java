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
import steadylah.SteadyLah;

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
    private final Image steadyLahImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/SteadyLah.png")));
    private SteadyLah steadyLah;

    /**
     * Bind scrollPane to dialogueContainer for scrolling beyond margins of scrollPane,
     * and program lazily searchButton to correctly execute on being clicked explicitly vs hovering/automatic.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogueContainer.heightProperty());
        this.searchButton.setOnAction(clickEvent -> handleUserInput());
    }

    /**
     * Instantiate steadyLah object solely in GUI mode (identical to in CLI mode) and bundle load functionality.
     * @param steadyLah new steadyLah instance.
     */
    public void setSteadyLah(SteadyLah steadyLah) {
        this.steadyLah = steadyLah;
        this.loadPreviousSession(); // Previous Session could be independently in CLI and/or GUI mode.
    }

    /**
     * Load from cache past data for persistence.
     */
    private void loadPreviousSession() {
        this.dialogueContainer.getChildren().add(
                DialogueBox.showSteadyLahDialogueBox(this.steadyLah.loadFromCache(), this.steadyLahImage)
        );
    }

    /**
     * Render each pair of custom user descriptionString and steadyLah bot's response, alongside their images,
     * as a pair of dialogueBoxes simultaneously rendered correctly solely in GUI mode, while handling "bye" command
     * like CLI mode's steadyLah::execute guard block.
     */
    @FXML
    public void handleUserInput() {
        String searchInput = this.searchBox.getText().trim();
        if (searchInput.equals("bye")) {
            this.dialogueContainer.getChildren().addAll(
                    DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                    DialogueBox.showSteadyLahDialogueBox(this.steadyLah.saveToCache(), this.steadyLahImage)
            );
            Platform.exit();
            return; // Terminate on reaching exit command
        }
        String steadyLahOutput = this.steadyLah.getResponse(searchInput);
        this.dialogueContainer.getChildren().addAll(
                DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                DialogueBox.showSteadyLahDialogueBox(steadyLahOutput, this.steadyLahImage)
        );
        this.searchBox.clear();
    }
}
