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

    //Billing bill = new Billing(0);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        patientName.setText(patient.getName());
        patientRecordButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"DashboardScreen.fxml","Dashboard");
        });
        anotherPatientButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Select Patient Type");
        });

        pharmacyButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"ChooseMedicine.fxml", " Choose a Medicine");
        });

        appointmentButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"AppointmentScreen.fxml", " Choose a Medicine");
        });

//        newPatientButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"PatientInfo.fxml", " Choose a Medicine");
//        });

        billingButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientInfoScreen.fxml", " Choose a Medicine");
        });

        labTestButton.setOnAction( e -> {
            ManagementUtils.changeScence(e, "Pathology.fxml", " Choose a Lab Test");
        });

        logOutButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"WelcomeScreen.fxml","Hello!");
        });

        billingButton.setOnAction( e -> {
            ManagementUtils.changeScence(e, "BillingScreen.fxml","Billing");
        });
    }


}
