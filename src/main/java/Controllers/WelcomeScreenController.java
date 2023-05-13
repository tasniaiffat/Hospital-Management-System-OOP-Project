package Controllers;


import Models.ManagementUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {

    @FXML
    private Button getStartedButton;

    @FXML
    private Button loginScreenButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getStartedButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"SignUpScreen.fxml","Login");
        });

        loginScreenButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"LogInScreen.fxml","Login");
        });
    }


}
