package Models;

import Controllers.ChoosePatientController;
import Models.ClassHierarchy.Gender;
import Models.ClassHierarchy.PatientInterface;
import Models.ClassHierarchy.Person;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
//            statement.setString(1,patientID);
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
            Logger.getLogger(ChoosePatientController.class.getName()).log(Level.SEVERE,null, e);
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
//        String connectQuery = "SELECT COUNT(*) FROM `hospital`.`patientinfo`;\n";
        String connectQuery = "SELECT ID from hospital.patientinfo ORDER BY ID DESC LIMIT 1";
//        int count=0;
        int IDNumber=1;


        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);

            ResultSet rs = statement.executeQuery();


            // Get the result
            if (rs.next()) {
//                count = rs.getInt(1);
//                System.out.println("Number of entries: " + count);
                String patientID = rs.getString(1).substring(3);
                IDNumber = Integer.parseInt(patientID);
                IDNumber++;
            }
//            count++;

        } catch (Exception s){
            s.printStackTrace();
        }
        return "PAT"+ new String(new char[6-(int)Math.log10(IDNumber)]).replace('\0', '0')+IDNumber;

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

    public boolean removePatient(Label errorMessage){
        boolean successfulRemove = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "DELETE FROM hospital.patientinfo WHERE ID= "+"'"+this.ID+"';";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);



            int status = statement.executeUpdate();

            if(status==1){
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
