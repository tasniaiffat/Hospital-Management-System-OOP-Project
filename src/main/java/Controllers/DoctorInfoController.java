package Controllers;

import Models.ClassHierarchy.Gender;
import Models.Doctor;
import Models.ManagementUtils;
//import Models.Patient;
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

public class DoctorInfoController implements Initializable {

    @FXML
    private Button addDoctorButton;

    @FXML
    private Button backbutton;


    @FXML
    private TextField newDoctorAddress;

    @FXML
    private TextField newDoctorContact;

    @FXML
    private DatePicker newDoctorDob;

    @FXML
    private TextField newDoctorEmail;

    @FXML
    private CheckBox newDoctorIsFemale;

    @FXML
    private CheckBox newDoctorIsMale;

    @FXML
    private Label newDoctorLabel;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField newDoctorName;

    @FXML
    private TextField qualification;

    @FXML
    private TextField Specialbox;

    @FXML
    private Button logOutButton;


    public void handleMaleBox(ActionEvent event){
        if(newDoctorIsMale.isSelected()) {
            newDoctorIsFemale.setSelected(false);
        }
    }
    public void handleFemaleBox(ActionEvent event){
        if(newDoctorIsFemale.isSelected()) {
            newDoctorIsMale.setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addDoctorButton.setOnAction( e -> {
            String DoctorName = newDoctorName.getText();
            LocalDate DoctorDOB = newDoctorDob.getValue();
            String DoctorContact = newDoctorContact.getText();
            String DoctorAddress = newDoctorAddress.getText();
            String DoctorEmail = newDoctorEmail.getText();
            String doctorqualification = qualification.getText();
            String speciality = Specialbox.getText();
            boolean isavailable = false;

            Gender DoctorGender = null;
            if(newDoctorIsMale.isSelected()) DoctorGender= Gender.Male;
            else if(newDoctorIsFemale.isSelected()) DoctorGender = Gender.Female;


            Doctor doctor = new Doctor(DoctorName,DoctorContact,DoctorEmail,DoctorAddress,DoctorDOB,DoctorGender,doctorqualification,speciality,isavailable );

            boolean successfulAdd;
            if (doctor.addDoctor(e, errorMessage)) successfulAdd = true;
            else successfulAdd = false;

            if(successfulAdd){
                ChoosePatientScreenController.chosenID = doctor.getID();
                ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
            }

        });

        backbutton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
        });
    }
}
