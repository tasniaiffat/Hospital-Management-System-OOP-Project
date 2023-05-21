package Controllers;

import Models.DBUtils;
import Models.ManagementUtils;
import Models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChoosePatientScreenController implements Initializable {

    @FXML
    private TableView<Patient> choosePatientTable;
    @FXML
    private TableColumn<Patient, String> choosePatientContactNo;

    @FXML
    private TableColumn<Patient, String> choosePatientEmailAddress;

    @FXML
    private TableColumn<Patient, String> choosePatientID;

    @FXML
    private TableColumn<Patient, String> choosePatientName;

    @FXML
    private TextField choosePatientTxtField;

    @FXML
    private Label errorMessage;

    @FXML
    private Button selectPatientButton;

    @FXML
    private Button removePatientButton;
    @FXML
    private Button newPatientButton;
    @FXML
    private Button backButton;

    ObservableList<Patient> patientList = FXCollections.observableArrayList();

    public static String chosenID = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchPatient();

        selectPatientButton.setOnAction( e -> {
            Patient selectedPatient = choosePatientTable.getSelectionModel().getSelectedItem();
            if (selectedPatient != null) {
                String ID = selectedPatient.getID();
                chosenID = ID;
                ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
            }
            else{
                errorMessage.setText("Patient Not Selected");
            }
        });

        removePatientButton.setOnAction( e -> {
            Patient selectedPatient = choosePatientTable.getSelectionModel().getSelectedItem();
            if (selectedPatient != null) {
                selectedPatient.removePatient(e,errorMessage);
            }
        });

        newPatientButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"PatientInfoScreen.fxml","Add new patient");
        });


        backButton.setOnAction( e -> {
                    ManagementUtils.changeScence(e, "PatientTypeScreen.fxml", "Hello!");
        });

//        returnButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Hello!");
//
//        });
        }


    public void searchPatient(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchPatientQuery = "SELECT `ID`, `Name`, `Email Address`, `Contact No` FROM hospital.patientinfo ;\n";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchPatientQuery);

            while(queryOutput.next()){
                String patientID = queryOutput.getString("ID");
                String patientName = queryOutput.getString("Name");
                String patientEmailAddress = queryOutput.getString("Email Address");
                String patientContactNo = queryOutput.getString("Contact No");

                patientList.add(new Patient(patientID,patientName,patientEmailAddress,patientContactNo));
            }


            choosePatientID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            choosePatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
            choosePatientEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
            choosePatientContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

            choosePatientTable.setItems(patientList);

            filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    public void filterList(){
        FilteredList<Patient> filteredList = new FilteredList<>(patientList, b -> true);

        choosePatientTxtField.textProperty().addListener( (observable, oldValue, newValue) -> {
            filteredList.setPredicate( Patient -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if(Patient.getName().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Patient.getID().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Patient.getEmailAddress().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Patient.getContactNo().toLowerCase().contains(searchKeyword)){
                    return true;
                } else return false;

            });
        });

        SortedList<Patient> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(choosePatientTable.comparatorProperty());

        choosePatientTable.setItems(sortedList);

    }

}
