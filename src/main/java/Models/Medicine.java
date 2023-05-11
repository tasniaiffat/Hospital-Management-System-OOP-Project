package Models;

import Models.ClassHierarchy.MedicineInterface;
import Models.ClassHierarchy.Service;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Medicine extends Service implements MedicineInterface {
    private String medicineName;
    private String medicineCompany;
    private double medicinePrice;
    private int quantityAvailable;

    public Medicine(String ID, Patient patient, LocalDate dateOfService, String medicineName,
                    String medicineCompany, double medicinePrice, int quantityAvailable) {
        super(ID, patient, dateOfService);
        this.medicineName = medicineName;
        this.medicineCompany = medicineCompany;
        this.medicinePrice = medicinePrice;
        this.quantityAvailable = quantityAvailable;
    }

    public Medicine(String medicineName, String medicineCompany, double medicinePrice, int quantityAvailable){
        this.ID = generateID();
        this.medicineName = medicineName;
        this.medicineCompany = medicineCompany;
        this.medicinePrice = medicinePrice;
        this.quantityAvailable = quantityAvailable;
    }

    public Medicine() {}

    @Override
    public boolean addMedicine(Label errorMessage, ActionEvent e){
        boolean successfulAdd = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        //String Speciality = "";
        //String history = String.join(",",medicalHistory);
        //String treatment = String.join(",",currentTreatment);
        //String date = this.getDate().toString();

        String connectQuery = "INSERT INTO `hospital`.`medicineinfo`(`ID`,`Medicine Name`,`Company`,`Price`,`Quantity`) values (?,?,?,?,?);\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,this.ID);
            statement.setString(2,this.medicineName);
            statement.setString(3,this.medicineCompany);
            statement.setDouble(4,this.medicinePrice);
            statement.setInt(5,this.quantityAvailable);
            // statement.setString(8,history);
            // statement.setString(9,treatment);


            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setTextFill(Color.web("#61cb34"));
                errorMessage.setText("Medicine Added!");
                successfulAdd = true;
            }
            else{
                errorMessage.setText("Unable to add Medicine");
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
        String connectQuery = "SELECT ID from hospital.medicineinfo ORDER BY ID DESC LIMIT 1";
//        int count=0;
        int IDNumber=1;


        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);

            ResultSet rs = statement.executeQuery();


            // Get the result
            if (rs.next()) {
                String medicineID = rs.getString(1).substring(3);
                IDNumber = Integer.parseInt(medicineID);
                IDNumber++;
            }
//            count++;

        } catch (Exception s){
            s.printStackTrace();
        }
        return "MED"+ new String(new char[6-(int)Math.log10(IDNumber)]).replace('\0', '0')+IDNumber;

    }

    public boolean removeMedicine(Label errorMessage, ActionEvent e){
        boolean successfulRemove = false;
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "DELETE FROM hospital.medicineinfo WHERE ID= "+"'"+this.ID+"';";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
//            statement.setString(1,this.ID);



            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setText("Medicine Removed!");
                successfulRemove = true;
            }
            else{
                errorMessage.setText("Unable to remove Medicine");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulRemove;
    }

}
