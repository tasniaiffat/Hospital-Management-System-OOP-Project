package Controllers;


import Models.ManagementUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionController implements Initializable{

    @FXML
    private Button newPatientButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newPatientButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientInfoScreen.fxml","Add new patient");
        });
    }
}
