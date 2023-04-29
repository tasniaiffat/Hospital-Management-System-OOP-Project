package Controllers;

import Models.ClassHierarchy.Gender;
import Models.ManagementUtils;
import Models.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PatientInfoController implements Initializable {

    @FXML
    private Button addPatientButton;

    @FXML
    private Button existingPatientButton;

    @FXML
    private TextField newPatientAddress;

    @FXML
    private TextField newPatientContact;

    @FXML
    private DatePicker newPatientDob;

    @FXML
    private TextField newPatientEmail;

    @FXML
    private CheckBox newPatientIsFemale;

    @FXML
    private CheckBox newPatientIsMale;

    @FXML
    private Label newPatientLabel;

    @FXML
    private Label errorMessage;

    @FXML
    private TextArea newPatientMedHistory;
    @FXML
    private TextArea newPatientCurrentTreatment;

    @FXML
    private TextField newPatientName;
    public void handleMaleBox(ActionEvent event){
        if(newPatientIsMale.isSelected()) {
            newPatientIsFemale.setSelected(false);
        }
    }
    public void handleFemaleBox(ActionEvent event){
        if(newPatientIsFemale.isSelected()) {
            newPatientIsMale.setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        existingPatientButton.setOnAction( e ->{
            ManagementUtils.changeScence(e,"ChoosePatientScreen.fxml","Choose Patient");
        });

        addPatientButton.setOnAction( e -> {
            String patientName = newPatientName.getText();
            LocalDate patientDOB = newPatientDob.getValue();
            String patientContact = newPatientContact.getText();
            String patientAddress = newPatientAddress.getText();
            String patientEmail = newPatientEmail.getText();

            Gender patientGender = null;
            if(newPatientIsMale.isSelected()) patientGender= Gender.Male;
            else if(newPatientIsFemale.isSelected()) patientGender = Gender.Female;

            String patientHistoryString = newPatientMedHistory.getText();
            ArrayList<String> patientHistory = new ArrayList<>(Arrays.asList(patientHistoryString.split(",")));
            for(int i = 0; i<patientHistory.size();i++){
                patientHistory.set(i,patientHistory.get(i).trim());
            }
            String patientCurrentTreatmentString = newPatientCurrentTreatment.getText();
            ArrayList<String> patientCurrentTreatment = new ArrayList<>(Arrays.asList(patientCurrentTreatmentString.split(",")));
            for(int i = 0; i<patientCurrentTreatment.size();i++){
                patientCurrentTreatment.set(i,patientCurrentTreatment.get(i).trim());
            }

            Patient patient = new Patient(patientName,patientContact,patientEmail,patientAddress,patientDOB,patientGender,patientHistory,patientCurrentTreatment);

            boolean successfulAdd = patient.addPatient(e,errorMessage);

            if(successfulAdd){
                ChoosePatientController.chosenID = patient.getID();
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds
                } catch (InterruptedException s) {
                    s.printStackTrace();
                }
                ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
            }

        });
    }
}

