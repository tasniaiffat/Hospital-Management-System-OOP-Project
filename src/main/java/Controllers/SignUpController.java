package Controllers;

import Models.ManagementUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {



    @FXML
    private Button logInScreenButton;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField signUpConfirmPass;

    @FXML
    private TextField signUpEmail;

    @FXML
    private PasswordField signUpPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logInScreenButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"LoginScreen.fxml","Login");
        });
    }
}
