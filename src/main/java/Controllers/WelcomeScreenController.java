package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class WelcomeScreenController {
    @FXML
    private Label welcomeText;
    @FXML // fx:id="welcome_borderpane"
    private BorderPane welcome_borderpane; // Value injected by FXMLLoader

    @FXML // fx:id="welcome_left"
    private AnchorPane welcome_left; // Value injected by FXMLLoader

    @FXML // fx:id="welome_right"
    private AnchorPane welome_right; // Value injected by FXMLLoader




    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}