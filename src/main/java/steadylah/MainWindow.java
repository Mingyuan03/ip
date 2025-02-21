package steadylah;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogueContainer.heightProperty());
        this.searchButton.setOnAction(clickEvent -> handleUserInput());
    }

    public void setSteadyLah(SteadyLah steadyLah) {
        this.steadyLah = steadyLah;
    }

    @FXML
    public void handleUserInput() {
        String searchInput = this.searchBox.getText();
        String steadyLahOutput = this.steadyLah.getResponse(searchInput);
        this.dialogueContainer.getChildren().addAll(
                DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                DialogueBox.showSteadyLahDialogueBox(steadyLahOutput, this.steadyLahImage)
        );
        this.searchBox.clear();
    }
}
