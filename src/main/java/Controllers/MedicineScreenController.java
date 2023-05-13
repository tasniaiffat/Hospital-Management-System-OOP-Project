package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineScreenController implements Initializable {


    @FXML
    private TableView<Medicine> MedicineTable;
    @FXML
    private TableColumn<Medicine, String> MedicineID;
    @FXML
    private TableColumn<Medicine, String> MedicineName;
    @FXML
    private TableColumn<Medicine, String> MedicinePrice;
    @FXML
    private TableColumn<Medicine, String> MedicineCompany;
    @FXML
    private TableColumn<Medicine, String> MedicineQuantity;
    @FXML
    private Button selectButton;
    @FXML
    private Button addButton;
    @FXML
    private Button returnButton;

    @FXML
    private Button PathologyConfirmButton;

    @FXML
    private Label errorMessage;
    ObservableList<Medicine> MedicineList = FXCollections.observableArrayList();


    public void searchMedicine(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchMedicineQuery = "SELECT `ID`, `Medicine Name`, `Company`,`Quantity`, `Price` FROM hospital.medicineinfo ;\n";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchMedicineQuery);

            while(queryOutput.next()){
                String MedicineID = queryOutput.getString("ID");
                String Medicinename = queryOutput.getString("Medicine Name");
                String MedicineCompany = queryOutput.getString("Company");
                Double Medicineprice = queryOutput.getDouble("Price");
                Integer MedicineQuantity = queryOutput.getInt("Quantity");

                MedicineList.add(new Medicine(MedicineID,Medicinename,MedicineCompany,Medicineprice,MedicineQuantity));
            }


            MedicineID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            MedicineName.setCellValueFactory(new PropertyValueFactory<>("MedicineName"));
            MedicineCompany.setCellValueFactory(new PropertyValueFactory<>("MedicineCompany"));
            MedicinePrice.setCellValueFactory(new PropertyValueFactory<>("MedicinePrice"));
            MedicineQuantity.setCellValueFactory(new PropertyValueFactory<>("MedicineQuantity"));

            MedicineTable.setItems(MedicineList);

            //filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchMedicine();

        selectButton.setOnAction( e -> {
            // Add to cart and update bill
        });
//        removeButton.setOnAction(( e -> {
//            Pathology selectedtest = PathologyTable.getSelectionModel().getSelectedItem();
//            if (selectedtest != null) {
//                selectedtest.removePathology(errorMessage,e);
//            }
//        }));

        PathologyConfirmButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
        });

        addButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"AddMedicine.fxml","Add a Labtest");
        });

        returnButton.setOnAction( e -> {
            ManagementUtils.changeScence(e, "ReceptionScreen.fxml","Hello!");
        });



    }
}

