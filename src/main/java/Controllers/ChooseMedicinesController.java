package Controllers;

import Models.DBUtils;
import Models.ManagementUtils;
import Models.Medicine;
import Models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChooseMedicinesController implements Initializable {
    @FXML
    private TableColumn<Medicine, Double> medPrice;
    @FXML
    private TableColumn<Medicine, Integer> medStock;
    @FXML
    private TableColumn<Medicine, String> medCompany;
    @FXML
    private TextField chooseMedTextField;
    @FXML
    private Button selectMedButton;
    @FXML
    public Button addMedButton;
    @FXML
    private Button removeMedButton;
    @FXML
    private Label errorMessage;
    @FXML
    private Button backButton;
    @FXML
    private Button updateStockButton;
    @FXML
    private TableColumn<Medicine, String> medName;
    @FXML
    private TableColumn<Medicine, String> medID;
    @FXML
    private TableView<Medicine> chooseMedTable;

    ObservableList<Medicine> medList = FXCollections.observableArrayList();
    public static String chosenID  = null;

    public void searchMedicine(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchMedicineQuery = "SELECT `ID`, `Medicine Name`, `Company`, `Price`,`Quantity` FROM hospital.medicineinfo ;\n";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchMedicineQuery);

            while(queryOutput.next()){
                String medID = queryOutput.getString("ID");
                String medname = queryOutput.getString("Medicine Name");
                String medcompany = queryOutput.getString("Company");
                Double medprice = queryOutput.getDouble("Price");
                int medquantity = queryOutput.getInt("Quantity");

                medList.add(new Medicine(medID,medname,medcompany,medprice,medquantity));
            }


            medID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            medName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
            medCompany.setCellValueFactory(new PropertyValueFactory<>("medicineCompany"));
            medPrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));
            medStock.setCellValueFactory(new PropertyValueFactory<>("quantityAvailable"));

            chooseMedTable.setItems(medList);

            //filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }

//    public void filterList(){
//        FilteredList<Medicine> filteredList = new FilteredList<>(medList, b -> true);
//
//        chooseMedTextField.textProperty().addListener( (observable, oldValue, newValue) -> {
//            filteredList.setPredicate( Patient -> {
//                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
//                    return true;
//                }
//
//                String searchKeyword = newValue.toLowerCase();
//
//                if(Medicine.getName().toLowerCase().contains(searchKeyword)){
//                    return true;
//                } else if(Medicine.getID().toLowerCase().contains(searchKeyword)){
//                    return true;
//                } else if(Medicine.getCompany().toLowerCase().contains(searchKeyword)){
//                    return true;
//                } else return false;
//
//            });
//        });
//
//        SortedList<Medicine> sortedList = new SortedList<>(filteredList);
//        sortedList.comparatorProperty().bind(chooseMedTable.comparatorProperty());
//
//        chooseMedTable.setItems(medList);
//
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchMedicine();

        selectMedButton.setOnAction( e -> {
            Medicine selectedMed = chooseMedTable.getSelectionModel().getSelectedItem();
            if (selectedMed != null) {
                String ID = selectedMed.getID();
                chosenID = ID;
                if(selectedMed.getQuantity()>0){
                    selectedMed.updateQuantity();
                    //update billing
                    boolean provided = selectedMed.provideService();
                    if(!provided){
                        errorMessage.setText("Service could not be provided");
                    }
                }
            }
            else{
                errorMessage.setText("Medicine Not Selected");
            }
        });

        removeMedButton.setOnAction( e -> {
            Medicine selectedMed = chooseMedTable.getSelectionModel().getSelectedItem();
            if (selectedMed != null) {
                selectedMed.removeMedicine(errorMessage,e);
            }
        });

//        addMedButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"AddMedicine.fxml","Add new Medicine");
//        });

        backButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
        });




    }

}
