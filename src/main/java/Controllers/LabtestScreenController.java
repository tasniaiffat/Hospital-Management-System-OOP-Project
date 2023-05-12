package Controllers;

import Models.ManagementUtils;
import Models.Pathology;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LabtestScreenController implements Initializable {

    @FXML
    private TextField newTestName;
    @FXML
    private TextField newTestPrice;
    @FXML
    private TextField newTestDesciption;
    @FXML
    private Button addPathologyButton;


    @FXML
    private Label errorMessage;
    @FXML
    private Button returnButton;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addPathologyButton.setOnAction( e -> {
            String testName = newTestName.getText();
            String Price = newTestPrice.getText();
            double testPrice = Double.parseDouble(Price);
            String testDescription = newTestDesciption.getText();


            Pathology pathology = new Pathology(testName,testDescription,testPrice);

            boolean successfulAdd = pathology.addPathology(errorMessage,e);

            if(successfulAdd){
                ChoosePatientScreenController.chosenID = pathology.getID();
                ManagementUtils.changeScence(e,"Pathology.fxml","Choose a Test");
            }

        });

        returnButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"WelcomeScreen.fxml","Hello!");
        });
        backButton.setOnAction(( e -> {
            ManagementUtils.changeScence(e,"Pathology.fxml","Choose a test");
        }));
    }


}

