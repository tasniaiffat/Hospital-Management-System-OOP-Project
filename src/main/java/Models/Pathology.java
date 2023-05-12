package Models;

import Models.ClassHierarchy.PathologyInterface;
import Models.ClassHierarchy.Service;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pathology extends Service implements PathologyInterface {
    private String testName;
    private String testDescription;
    private double testPrice;

    private String results;

    public String getTestName() {
        return testName;
    }

    public double getTestPrice() {
        return testPrice;
    }

    public String getTestDescription() {
        return testDescription;
    }
    public void setTestName(String name){this.testName = name;}
    public void setTestDescription(String desc){this.testDescription = desc ;}
    public void setTestPrice(double price){this.testPrice = price;}


    public Pathology(String testName, String testDescription, double testPrice){
        this.ID = generateID();
        this.testName = testName;
        this.testDescription = testDescription;
        this.testPrice = testPrice;
    }

    public Pathology(String ID, String name, String Description, double price){
        this.ID = ID;
        this.testName = name;
        this.testDescription = Description;
        this.testPrice = price;
    }

    public Pathology(String pathologyID){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String selectDoctorQuery = "SELECT * FROM hospital.Pathologyinfo WHERE ID = "+"'"+pathologyID+"'";



        try{
            PreparedStatement statement = connectDB.prepareStatement(selectDoctorQuery);
//            statement.setString(1,patientID);
            ResultSet queryOutput = statement.executeQuery(selectDoctorQuery);

            if(queryOutput.next()){
                this.ID = queryOutput.getString("ID");
                this.testName = queryOutput.getString("Test Name");
                this.testDescription = queryOutput.getString("Test Description");
                this.testPrice = queryOutput.getDouble("Price");
                //this.quantityAvailable = queryOutput.getInt("Quantity");
//                this.address = queryOutput.getString("Address");
//                this.date = queryOutput.getDate("Date of Birth").toLocalDate();
//                this.qualification = queryOutput.getString("Qualification");
//                if(queryOutput.getString("Gender").equals("Male"))
//                    this.gender = Gender.Male;
//                else if(queryOutput.getString("Gender").equals("Female"))
//                    this.gender = Gender.Female;

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
    public boolean updateTestResults(){
        return true;
    }

    @Override
    public boolean provideService() {
        return super.provideService();
    }


    public boolean addPathology(Label errorMessage, ActionEvent e){
        boolean successfulAdd = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        //String Speciality = "";
        //String history = String.join(",",medicalHistory);
        //String treatment = String.join(",",currentTreatment);
        //String date = this.getDate().toString();

        String connectQuery = "INSERT INTO `hospital`.`Pathologyinfo`(`ID`,`Test Name`,`Test Description`,`Price`) values (?,?,?,?);\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,this.ID);
            statement.setString(2,this.testName);
            statement.setString(3,this.testDescription);
            statement.setDouble(4,this.testPrice);
            //statement.setInt(5,this.quantityAvailable);
            // statement.setString(8,history);
            // statement.setString(9,treatment);


            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setTextFill(Color.web("#61cb34"));
                errorMessage.setText("Labtest Added!");
                successfulAdd = true;
            }
            else{
                errorMessage.setText("Unable to add LabTest");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulAdd;
    }

    public String generateID(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
//        String connectQuery = "SELECT COUNT(*) FROM `hospital`.`patientinfo`;\n";
        String connectQuery = "SELECT ID from hospital.Pathologyinfo ORDER BY ID DESC LIMIT 1";
//        int count=0;
        int IDNumber=1;


        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);

            ResultSet rs = statement.executeQuery();


            // Get the result
            if (rs.next()) {
                String pathologyID = rs.getString(1).substring(3);
                IDNumber = Integer.parseInt(pathologyID);
                IDNumber++;
            }
//            count++;

        } catch (Exception s){
            s.printStackTrace();
        }
        return "LAB"+ new String(new char[6-(int)Math.log10(IDNumber)]).replace('\0', '0')+IDNumber;

    }

    public boolean removePathology(Label errorMessage, ActionEvent e){
        boolean successfulRemove = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "DELETE FROM hospital.Pathologyinfo WHERE ID= "+"'"+this.ID+"';";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
//            statement.setString(1,this.ID);



            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setText( "Lab Test Removed!");
                successfulRemove = true;
            }
            else{
                errorMessage.setText("Unable to remove Lab Test");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulRemove;
    }



}
