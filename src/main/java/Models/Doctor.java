package Models;

import Models.ClassHierarchy.DoctorInterface;
import Models.ClassHierarchy.Gender;
import Models.ClassHierarchy.Person;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.*;
import java.time.LocalDate;

public class Doctor extends Person implements DoctorInterface{
    private String qualification;
    private String speciality;

    private boolean isAvailable;

    public Doctor(String name, String contactNo, String emailAddress, String address, LocalDate date, Gender gender, String qualification, String speciality, boolean isAvailable) {
        super(name, contactNo, emailAddress, address, date, gender);
        this.ID = generateID();
        this.qualification = qualification;
        this.speciality = speciality;
        this.isAvailable = isAvailable;
    }

    public Doctor(String ID,String name, String speciality, String contactNo) {
        super();
        this.ID = ID;
        this.name = name;
        this.speciality = speciality;
        this.contactNo = contactNo;
    }


    public Doctor() {}

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Doctor(String DoctorID){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String selectDoctorQuery = "SELECT * FROM hospital.doctorinfo WHERE ID = "+"'"+DoctorID+"'";



        try{
            PreparedStatement statement = connectDB.prepareStatement(selectDoctorQuery);
//            statement.setString(1,patientID);
            ResultSet queryOutput = statement.executeQuery(selectDoctorQuery);

            if(queryOutput.next()){
                this.ID = queryOutput.getString("ID");
                this.name = "Dr. "+queryOutput.getString("Name");
                this.speciality = queryOutput.getString("Speciality");
                this.contactNo = queryOutput.getString("Contact No");
                this.emailAddress = queryOutput.getString("Email Address");
                this.address = queryOutput.getString("Address");
                this.date = queryOutput.getDate("Date of Birth").toLocalDate();
                this.qualification = queryOutput.getString("Qualification");
                if(queryOutput.getString("Gender").equals("Male"))
                    this.gender = Gender.Male;
                else if(queryOutput.getString("Gender").equals("Female"))
                    this.gender = Gender.Female;

//                ArrayList<String> patientHistory = new ArrayList<>(Arrays.asList(queryOutput.getString("MedicalHistory").split(",")));
//                for(int i = 0; i<patientHistory.size();i++) {
//                    patientHistory.set(i, patientHistory.get(i).trim());
//                }

                //ArrayList<String> patientCurrTreatment = new ArrayList<>(Arrays.asList(queryOutput.getString("Current Treatment").split(",")));
//                for(int i = 0; i<patientCurrTreatment.size();i++){
//                    patientCurrTreatment.set(i,patientCurrTreatment.get(i).trim());
//                }
//                this.currentTreatment = patientCurrTreatment;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String generateID(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
//        String connectQuery = "SELECT COUNT(*) FROM `hospital`.`patientinfo`;\n";
        String connectQuery = "SELECT ID from hospital.doctorinfo ORDER BY ID DESC LIMIT 1";
//        int count=0;
        int IDNumber=1;


        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);

            ResultSet rs = statement.executeQuery();


            // Get the result
            if (rs.next()) {
                String DoctorID = rs.getString(1).substring(3);
                IDNumber = Integer.parseInt(DoctorID);
                IDNumber++;
            }
//            count++;

        } catch (Exception s){
            s.printStackTrace();
        }
        return "DOC"+ new String(new char[6-(int)Math.log10(IDNumber)]).replace('\0', '0')+IDNumber;

    }

    public boolean addDoctor(ActionEvent e, Label errorMessage){
        boolean successfulAdd = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String Speciality = "";
        Speciality = this.speciality;
        //String history = String.join(",",medicalHistory);
        //String treatment = String.join(",",currentTreatment);
        String date = this.getDate().toString();

        String connectQuery = "INSERT INTO `hospital`.`doctorinfo`(`ID`,`Name`,`Speciality`,`Contact No`,`Email Address`,`Address`,`Date of Birth`,`Gender`,`Qualification`) values (?,?,?,?,?,?,?,?,?);\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,this.getID());
            statement.setString(2,this.getName());
            statement.setString(3,Speciality);
            statement.setString(4,this.getContactNo());
            statement.setString(5,this.getEmailAddress());
            statement.setString(6,this.getAddress());
            statement.setString(7,date);
            statement.setString(8,this.getGender().toString());
            statement.setString(9,this.getQualification());
            // statement.setString(8,history);
            // statement.setString(9,treatment);


            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setTextFill(Color.web("#61cb34"));
                errorMessage.setText("Doctor Added!");
                successfulAdd = true;
            }
            else{
                errorMessage.setText("Unable to add Doctor");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulAdd;
    }

    public boolean removeDoctor(Label errorMessage, ActionEvent e){
        boolean successfulRemove = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "DELETE FROM hospital.doctorinfo WHERE ID= "+"'"+this.ID+"';";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
//            statement.setString(1,this.ID);



            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setText("Doctor Removed!");
                successfulRemove = true;
            }
            else{
                errorMessage.setText("Unable to remove Doctor");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulRemove;
    }




}

