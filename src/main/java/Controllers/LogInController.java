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

public class LogInController implements Initializable{
    @FXML
    private Button loginButton;

    @FXML
    private TextField loginEmail;

    @FXML
    private PasswordField loginPass;

    @FXML
    private Button signUpScreenButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpScreenButton.setOnAction( e ->{
            ManagementUtils.changeScence(e,"SignUpScreen.fxml","Sign Up");
        });
    }
}
