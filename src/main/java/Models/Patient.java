package Models;

import Models.ClassHierarchy.Gender;
import Models.ClassHierarchy.PatientInterface;
import Models.ClassHierarchy.Person;
import Models.DBUtils;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Person implements PatientInterface {
    private List<String> medicalHistory;
    private List<String> currentTreatment;

    public Patient(String ID, String name, String contactNo, String emailAddress, String address, Date date, Gender gender,
                   ArrayList<String> medicalHistory, ArrayList<String> currentTreatment) {
        super(ID, name, contactNo,emailAddress, address, date, gender);
        this.medicalHistory = medicalHistory;
        this.currentTreatment = currentTreatment;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<String> getCurrentTreatment() {
        return currentTreatment;
    }

    public void setCurrentTreatment(List<String> currentTreatment) {
        this.currentTreatment = currentTreatment;
    }

    public boolean addPatient(Label errorMessage){
        boolean successfulAdd = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String history = String.join(",",medicalHistory);
        String treatment = String.join(",",currentTreatment);
        String date = this.getDate().toString();

        String connectQuery = "INSERT INTO `hospital`.`patientinfo`(`ID`,`Name`,`Contact No`,`Email Address`,`Address`,`Date of Birth`,`Gender`,`Medical History`,`Current Treatment`) values (?,?,?,?,?,?,?,?,?);\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,this.getID());
            statement.setString(2,this.getName());
            statement.setString(3,this.getContactNo());
            statement.setString(4,this.getEmailAddress());
            statement.setString(5,this.getAddress());
            statement.setString(6,date);
            statement.setString(7,this.getGender().toString());
            statement.setString(8,history);
            statement.setString(9,treatment);


            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setTextFill(Color.web("#61cb34"));
                errorMessage.setText("Patient Added!");
                successfulAdd = true;
            }
            else{
                errorMessage.setText("Unable to add patient");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulAdd;
    }

    public boolean removePatient(String ID, Label errorMessage){
        boolean successfulRemove = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "DELETE FROM hospital.patientinfo WHERE ID=? ;\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,ID);



            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setTextFill(Color.web("#61cb34"));
                errorMessage.setText("Patient Removed!");
                successfulRemove = true;
            }
            else{
                errorMessage.setText("Unable to remove patient");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulRemove;
    }

}
