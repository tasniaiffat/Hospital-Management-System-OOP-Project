package Controllers;

import Models.ManagementUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientTypeController implements Initializable {

    @FXML
    private Button existingPatientButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private ImageView patientTypeImage;

    @FXML
    private Label patientTypeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newPatientButton.setOnAction( e ->{
            ManagementUtils.changeScence(e,"PatientInfoScreen.fxml","Add New Patient");
        });

        existingPatientButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
            ManagementUtils.changeScence(e,"ChoosePatientScreen.fxml","Choose Patient");
        });

    }

}

