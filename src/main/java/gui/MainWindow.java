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
import yasumax.YasuMax;

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
            getClass().getResourceAsStream("/images/Hiro.png")));
    private final Image yasuMaxImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/YasuMax.png")));
    private YasuMax yasuMax;

    /**
     * Enable dynamic scrolling of main window as dialogues exceed its margins; implement stopgap colour designation for
     * background of main window, and programme search button to enter user's input only if explicitly clicked/entered.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogueContainer.heightProperty());
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #EF7C00, #003D7C);");
        this.searchButton.setOnAction(clickEvent -> handleUserInput());
    }

    /**
     * Bundle new bot's instantiation and cache load in GUI-mode only.
     * @param yasuMax New bot instance.
     */
    public void setYasuMax(YasuMax yasuMax) {
        this.yasuMax = yasuMax;
        this.dialogueContainer.getChildren().add(
                DialogueBox.showYasuMaxDialogueBox(this.yasuMax.loadTasksFromCache(), this.yasuMaxImage));
    }

    /**
     * Generate bot's speech bubble output like CLI-mode's this::execute, providing custom help message handling and
     * program termination via `bye` command.
     */
    @FXML
    public void handleUserInput() {
        String searchInput = this.searchBox.getText().trim();
        this.searchBox.clear();
        if (searchInput.equals("bye")) {
            this.dialogueContainer.getChildren().addAll(
                    DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                    DialogueBox.showYasuMaxDialogueBox(this.yasuMax.saveTasksToCache(), this.yasuMaxImage)
            );
            Platform.exit(); // Terminate on reaching exit command
            return;
        }
        String[] yasuMaxOutput = this.yasuMax.processCommandByMap(searchInput);
        assert yasuMaxOutput.length == 2
                : "Error in processing user input: yasumax bot must output only response and whether help is needed";
        if (yasuMaxOutput[1].equals("true")) {
            // this.dialogueContainer.getChildren().add(DialogueBox.showUserDialogueBox(searchInput, this.userImage));
            new HelpBox(yasuMaxOutput[0]).showAndWait();
            return;
        }
        this.dialogueContainer.getChildren().addAll(
                DialogueBox.showUserDialogueBox(searchInput, this.userImage),
                DialogueBox.showYasuMaxDialogueBox(yasuMaxOutput[0], this.yasuMaxImage)
        );
    }
}
