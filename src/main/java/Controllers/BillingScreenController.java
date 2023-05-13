package Controllers;

import Models.Billing;
import Models.ManagementUtils;
import Models.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Controllers.ChoosePatientScreenController.chosenID;

public class BillingScreenController implements Initializable {


    @FXML
    private TextField amountPaidField;
    @FXML
    private Button backButton;

    @FXML
    private Label changesLabel;

    @FXML
    private Button confirmPaymentButton;

    @FXML
    private Label contactNoLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label idLabel;
    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button logOutButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label paymentStatusLabel;

    @FXML
    private Label totalamountLabel;

    public static Billing bill=new Billing();
//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String ID = chosenID;

        Patient patient = new Patient(ID);
        bill.setPatient(patient);
        bill.setID(ID);

        idLabel.setText(patient.getID());
        nameLabel.setText(patient.getName());
        contactNoLabel.setText(patient.getContactNo());
        emailLabel.setText(patient.getEmailAddress());

        totalamountLabel.setText(String.valueOf(bill.getBillingAmount()) + " ৳");
        amountPaidField.textProperty().addListener((observable, oldValue, newValue) -> {
            String amountStr = totalamountLabel.getText();

            double changeAmount = 0.00,paidAmount;
            double bill = Double.parseDouble(amountStr.substring(0,amountStr.length()-2));

            if(newValue.equals("")) paidAmount = 0.00;
            else{
                try{
                    paidAmount = Double.parseDouble(newValue);
                    if(bill==0) changeAmount = paidAmount;
                    else  changeAmount = paidAmount-bill;
                } catch (Exception e){
                    paidAmount = 0.00;
                    changeAmount = 0.00;
                }
            }

            
            changesLabel.setText(String.valueOf(changeAmount)+" ৳");

        });

//        backButton.setOnAction( e -> {
//            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Reception");
//        });

        logOutButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"ReceptionScreen.fxml","Hello");
        });

        confirmPaymentButton.setOnAction( e -> {

            try{
                String paidStr = amountPaidField.getText();
                Double paidAmount = Double.parseDouble(paidStr);

                String changeStr = changesLabel.getText();
                String changeAmount = changeStr.substring(0,changeStr.length()-2);

                if(Double.parseDouble(changeAmount)<0) throw new Exception();

                if(bill.provideService()){
                    paymentStatusLabel.setTextFill(Color.web("#61cb34"));
                    paymentStatusLabel.setText("Paid");

                    errorMessageLabel.setTextFill(Color.web("#61cb34"));
                    errorMessageLabel.setText("Payment completed");
                }

            } catch (Exception f){
                errorMessageLabel.setTextFill(Color.web("#e40707"));
                errorMessageLabel.setText("Complete Payment properly!");
            }

        });
    }
}
