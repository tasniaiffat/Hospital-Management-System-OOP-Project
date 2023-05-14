package Controllers;

import Models.*;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.join;
import static Controllers.ChoosePatientScreenController.chosenID;

public class AppointmentScreenController implements Initializable {

    @FXML
    private TableView<Doctor> chooseDoctorTable;
    @FXML
    private TableColumn<Doctor, String> chooseDoctorContactNo;

    @FXML
    private TableColumn<Doctor, String> chooseDoctorSpeciality;

    @FXML
    private TableColumn<Doctor, String> chooseDoctorID;

    @FXML
    private TableColumn<Doctor, String> chooseDoctorName;

    @FXML
    private TextField chooseDoctorTxtField;

    @FXML
    private Label errorMessage;

    @FXML
    private Button selectDoctorButton;

    @FXML
    private Button removeDoctorButton;
    @FXML
    private Button newDoctorButton;
    @FXML
    private Button returnButton;
    ObservableList<Doctor> DoctorList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchDoctor();

        selectDoctorButton.setOnAction( e -> {
            Doctor selectedDoctor = chooseDoctorTable.getSelectionModel().getSelectedItem();
            if (selectedDoctor != null) {
                String ID = selectedDoctor.getID();
                String name = selectedDoctor.getName();
                String speciality= selectedDoctor.getSpeciality();

                DBUtils connectNow = new DBUtils();
                Connection connectDB = connectNow.getConnection();
                String appointmentStr = "Consulted "+name+" ("+speciality+")";
                Patient patient = new Patient(chosenID);
                List<String> patientList = patient.getCurrentTreatment();
                patientList.add(appointmentStr);
                appointmentStr = join(", ",patientList);
//                System.out.println(appointmentStr);


                try {
                    String updateStockQuery = "UPDATE hospital.patientinfo SET `Current Treatment` = ?";
                    PreparedStatement statement = connectDB.prepareStatement(updateStockQuery);

                    // Set the new value for the VARCHAR column
                    statement.setString(1, appointmentStr);

                    int rowsAffected = statement.executeUpdate();

                    statement.close();
                    connectDB.close();
                } catch (SQLException f) {
                    System.out.println("SQL ERROR in Appointment choose: " + f.getMessage());
                    f.printStackTrace();
                }


//                chosenID = ID;
                errorMessage.setText("Appointment Made");
                ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
            }
            else{
                errorMessage.setText("Doctor Not Selected");
            }
        });

        removeDoctorButton.setOnAction( e -> {
            Doctor selectedDoctor = chooseDoctorTable.getSelectionModel().getSelectedItem();
            if (selectedDoctor != null) {
                selectedDoctor.removeDoctor(errorMessage,e);
            }
            ManagementUtils.changeScence(e,"AppointmentScreen.fxml","Appointment");
        });

//        newDoctorButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"DoctorInfoScreen.fxml","Add new patient");
//        });

        returnButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Hello!");
        });

    }

    public void searchDoctor(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchDoctorQuery = "SELECT `ID`, `Name`, `Speciality`, `Contact No` FROM hospital.doctorinfo ;\n";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchDoctorQuery);

            while(queryOutput.next()){
                String DoctorID = queryOutput.getString("ID");
                String DoctorName = queryOutput.getString("Name");
                String DoctorSpeciality = queryOutput.getString("Speciality");
                String DoctorContactNo = queryOutput.getString("Contact No");

                DoctorList.add(new Doctor(DoctorID,DoctorName,DoctorSpeciality,DoctorContactNo));
            }


            chooseDoctorID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            chooseDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
            chooseDoctorSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
            chooseDoctorContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

            chooseDoctorTable.setItems(DoctorList);

            filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    public void filterList(){
        FilteredList<Doctor> filteredList = new FilteredList<>(DoctorList, b -> true);

        chooseDoctorTxtField.textProperty().addListener( (observable, oldValue, newValue) -> {
            filteredList.setPredicate( Doctor -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if(Doctor.getName().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Doctor.getID().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Doctor.getSpeciality().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Doctor.getContactNo().toLowerCase().contains(searchKeyword)){
                    return true;
                } else return false;

            });
        });

        SortedList<Doctor> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(chooseDoctorTable.comparatorProperty());

        chooseDoctorTable.setItems(sortedList);

    }

}
