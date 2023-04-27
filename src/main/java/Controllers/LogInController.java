package Controllers;

import Models.Receptionist;
import Models.ManagementUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label errorMessage;
    Receptionist receptionist = new Receptionist();
    boolean successfulLogin = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpScreenButton.setOnAction( e ->{
            ManagementUtils.changeScence(e,"SignUpScreen.fxml","Sign Up");
        });

        loginButton.setOnAction( e -> {
            successfulLogin = receptionist.logIn(loginEmail,loginPass,errorMessage);

            if(successfulLogin){
                ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Select Patient Type");
            }
        });

    }
}
