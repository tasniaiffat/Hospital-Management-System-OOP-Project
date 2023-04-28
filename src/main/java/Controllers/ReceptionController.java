package Controllers;


import Models.ClassHierarchy.Gender;
import Models.DBUtils;
import Models.ManagementUtils;
import Models.Patient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controllers.ChoosePatientController.*;


public class ReceptionController implements Initializable{

    String ID = ChoosePatientController.chosenID;

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
    Patient patient = new Patient(ID);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        patientRecordButton.setOnAction( e -> {
            System.out.println(patient.getID());
            System.out.println(patient.getName());
            System.out.println(patient.getEmailAddress());
        });
        anotherPatientButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Select Patient Type");
        });

    }


}
