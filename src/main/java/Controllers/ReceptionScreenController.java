package Controllers;


import Models.ManagementUtils;
import Models.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class ReceptionScreenController implements Initializable{

    String ID = ChoosePatientScreenController.chosenID;

    @FXML
    private Button appointmentButton;

    @FXML
    private Button billingButton;

    @FXML
    private Button labTestButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private Button patientRecordButton;

    @FXML
    private Button pharmacyButton;

    @FXML
    private Button anotherPatientButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Label patientName;

    Patient patient = new Patient(ID);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        patientName.setText(patient.getName());
        patientRecordButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"DashboardScreen.fxml","Dashboard");
        });
        anotherPatientButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Select Patient Type");
        });

        logOutButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"WelcomeScreen.fxml","Hello!");
        });

    }


}
