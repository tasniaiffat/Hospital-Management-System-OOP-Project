package Controllers;

import Models.ClassHierarchy.Gender;
import Models.ManagementUtils;
import Models.Patient;
import Models.Medicine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddMedicineController implements Initializable {


    @FXML
    public TextField newMedName;
    @FXML
    public TextField newMedPrice;
    @FXML
    public TextField newMedStock;
    @FXML
    public Button addMedicineButton;
    @FXML
    public Label newMedLabel;
    @FXML
    public Label errorMessage;
    @FXML
    public Button returnButton;

    @FXML
    public Button refreshButton;
    @FXML
    public TextField newmedcompany;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        addMedicineButton.setOnAction( e -> {
            try{
                String MedName = newMedName.getText();
                String MedCompany = newmedcompany.getText();
                String price = newMedPrice.getText();
                double newMedPrice = Double.parseDouble(price);
                String stock = newMedStock.getText();
                int newMedStock = Integer.parseInt(stock);



                Medicine medicine = new Medicine(MedName,MedCompany,newMedPrice,newMedStock);
                String ID = medicine.getID();
                boolean successfulAdd = medicine.addMedicine(errorMessage,e);

                if(successfulAdd){
                    //ChoosePatientScreenController.chosenID = medicine.getID();
//                ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Choose a Patient");
                }
            }  catch (Exception f){
                errorMessage.setTextFill(Color.web("#e40707"));
                errorMessage.setText("Please fill out all fields");
            }


        });

        returnButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Choose a Medicine");
        });

        refreshButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"AddMedicine.fxml","Choose a Medicine");
        });
    }
}
