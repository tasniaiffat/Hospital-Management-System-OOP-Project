package Models;

import Models.ClassHierarchy.Gender;
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
    protected int quantityAvailable;
    public void setMedicineName(String name){this.medicineName = name;}
    public String getMedicineName(){return medicineName;}
    public void setMedicineCompany(String company){ this.medicineCompany = company;}
    public String getMedicineCompany(){return this.medicineCompany;}
    public double getMedicinePrice(){return medicinePrice;}
    public void setMedicinePrice(double price){this.medicinePrice = price;}
    public void setQuantityAvailable(int ava){this.quantityAvailable = ava;}
    public int getQuantityAvailable(){return this.quantityAvailable;}

    public int getQuantity(){ return quantityAvailable;}

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

    public void updateQuantity(){
        if(quantityAvailable!=0) quantityAvailable--;
    }


    public Medicine(String ID, String name, String Company, Double price, int quantity){
        this.ID = ID;
        this.medicineName = name;
        this.medicineCompany = Company;
        this.medicinePrice = price;
        this.quantityAvailable = quantity;
    }

    public Medicine(String medicineID){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String selectDoctorQuery = "SELECT * FROM hospital.medicineinfo WHERE ID = "+"'"+medicineID+"'";



        try{
            PreparedStatement statement = connectDB.prepareStatement(selectDoctorQuery);
//            statement.setString(1,patientID);
            ResultSet queryOutput = statement.executeQuery(selectDoctorQuery);

            if(queryOutput.next()){
                this.ID = queryOutput.getString("ID");
                this.medicineName = queryOutput.getString("Medicine Name");
                this.medicineCompany = queryOutput.getString("Company");
                this.medicinePrice = queryOutput.getDouble("Price");
                this.quantityAvailable = queryOutput.getInt("Quantity");
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
