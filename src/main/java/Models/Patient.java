package Models;

import Controllers.ChoosePatientScreenController;
import Models.ClassHierarchy.Gender;
import Models.ClassHierarchy.PatientInterface;
import Models.ClassHierarchy.Person;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Patient extends Person implements PatientInterface {

    private List<String> medicalHistory;
    private List<String> currentTreatment;


    public Patient(String name, String contactNo, String emailAddress, String address, LocalDate date, Gender gender,
                   ArrayList<String> medicalHistory, ArrayList<String> currentTreatment) {
        super(name, contactNo,emailAddress, address, date, gender);

        this.ID = generateID();
        this.medicalHistory = medicalHistory;
        this.currentTreatment = currentTreatment;

    }

    public Patient(String ID,String name, String emailAddress, String contactNo) {
        super();
        this.ID = ID;
        this.name = name;
        this.emailAddress = emailAddress;
        this.contactNo = contactNo;
    }

    public Patient() {}

    public Patient(String patientID){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String selectPatientQuery = "SELECT * FROM patientinfo WHERE ID = "+"'"+patientID+"'";



        try{
            PreparedStatement statement = connectDB.prepareStatement(selectPatientQuery);
            ResultSet queryOutput = statement.executeQuery(selectPatientQuery);

           if(queryOutput.next()){
               this.ID = queryOutput.getString("ID");
               this.name = queryOutput.getString("Name");
               this.contactNo = queryOutput.getString("Contact No");
               this.emailAddress = queryOutput.getString("Email Address");
               this.address = queryOutput.getString("Address");
               this.date = queryOutput.getDate("Date of Birth").toLocalDate();
               if(queryOutput.getString("Gender").equals("Male"))
                   this.gender = Gender.Male;
               else if(queryOutput.getString("Gender").equals("Female"))
                   this.gender = Gender.Female;

               ArrayList<String> patientHistory = new ArrayList<>(Arrays.asList(queryOutput.getString("Medical History").split(",")));
               for(int i = 0; i<patientHistory.size();i++){
                   patientHistory.set(i,patientHistory.get(i).trim());
               }
               this.medicalHistory = patientHistory;

               ArrayList<String> patientCurrTreatment = new ArrayList<>(Arrays.asList(queryOutput.getString("Current Treatment").split(",")));
               for(int i = 0; i<patientCurrTreatment.size();i++){
                   patientCurrTreatment.set(i,patientCurrTreatment.get(i).trim());
               }
               this.currentTreatment = patientCurrTreatment;
           }

        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
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




    public String generateID(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String connectQuery = "SELECT ID from hospital.patientinfo ORDER BY ID DESC LIMIT 1";

        int IDNumber=1;


        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);

            ResultSet rs = statement.executeQuery();


            // Get the result
            if (rs.next()) {
                String patientID = rs.getString(1).substring(3);
                IDNumber = Integer.parseInt(patientID);
                IDNumber++;
            }

        } catch (Exception s){
            s.printStackTrace();
        }
        return "PAT"+ new String(new char[6-(int)Math.log10(IDNumber)]).replace('\0', '0')+IDNumber;

    }

    public boolean addPatient(ActionEvent e, Label errorMessage){
        boolean successfulAdd = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String history = String.join(",",medicalHistory);
        String treatment = String.join(",",currentTreatment);
        String date = null;
        if(this.date != null)
            date = this.date.toString();
        if(this.ID==null || this.name==null || this.contactNo==null || this.emailAddress==null || this.address==null || this.date == null
                || this.gender==null || this.medicalHistory==null || this.currentTreatment == null){
            errorMessage.setText("You have to fill up all the fields!");
            return successfulAdd;
        }

        String connectQuery = "INSERT INTO `hospital`.`patientinfo`(`ID`,`Name`,`Contact No`,`Email Address`,`Address`,`Date of Birth`,`Gender`,`Medical History`,`Current Treatment`) values (?,?,?,?,?,?,?,?,?);\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,this.ID);
            statement.setString(2,this.name);
            statement.setString(3,this.contactNo);
            statement.setString(4,this.emailAddress);
            statement.setString(5,this.address);
            statement.setString(6,date);
            statement.setString(7,this.gender.toString());
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

    public boolean removePatient(Label errorMessage, ActionEvent e){
        boolean successfulRemove = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "DELETE FROM hospital.patientinfo WHERE ID= "+"'"+this.ID+"';";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            int status = statement.executeUpdate();
            ManagementUtils.changeScence(e,"ChoosePatientScreen.fxml","Choose Patient");
            if(status==1){
//                errorMessage.setText("Patient Removed!");
                successfulRemove = true;
            }

        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulRemove;
    }

}
