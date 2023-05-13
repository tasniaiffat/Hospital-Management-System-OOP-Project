package Controllers;

import Models.ClassHierarchy.Gender;
import Models.ManagementUtils;
import Models.Patient;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static Controllers.ChoosePatientScreenController.chosenID;

public class DashboardScreenController implements Initializable {

    @FXML
    private Label addressLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label contactNoLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private TableColumn<String, String> currTreatmentTableColumn;

    @FXML
    private TableView<String> currTreatmentTableView;
    @FXML
    private TableColumn<String, String> historyTableColumn;

    @FXML
    private TableView<String> historyTableView;

    @FXML
    private Label idLabel;

    @FXML
    private Button logOutButton;

    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String ID = chosenID;

        Patient patient = new Patient(ID);

        idLabel.setText(patient.getID());
        nameLabel.setText(patient.getName());
        contactNoLabel.setText(patient.getContactNo());
        emailLabel.setText(patient.getEmailAddress());
        addressLabel.setText(patient.getAddress());
        dobLabel.setText(patient.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if(patient.getGender()== Gender.Male) genderLabel.setText("Male");
        else if(patient.getGender()==Gender.Female) genderLabel.setText("Female");

        List<String> history =  patient.getMedicalHistory();

        List<String> currTreatment =  patient.getCurrentTreatment();


        historyTableColumn.setCellValueFactory(cellData -> {
            int index = cellData.getTableView().getItems().indexOf(cellData.getValue());
            String string = history.get(index);
            return new SimpleStringProperty(string);
        });
//        historyTableView.getColumns().add(historyTableColumn);
        historyTableView.setItems(FXCollections.observableArrayList(history));

        currTreatmentTableColumn.setCellValueFactory(cellData -> {
            int index = cellData.getTableView().getItems().indexOf(cellData.getValue());
            String string = currTreatment.get(index);
            return new SimpleStringProperty(string);
        });
//        currTreatmentTableView.getColumns().add(currTreatmentTableColumn);
        currTreatmentTableView.setItems(FXCollections.observableArrayList(currTreatment));



//        backButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
//        });

        logOutButton.setOnAction( e -> {
            ManagementUtils.changeScence(e, "ReceptionScreen.fxml","Hello!");
        });
    }
}

