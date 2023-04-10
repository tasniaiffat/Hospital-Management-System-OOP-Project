package Models.ClassHierarchy;

import Models.DBUtils;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Receptionist {
    private String emailAddress;
    private String password;

    private boolean successfulLogIn = false;
    private boolean successfulSignUp = false;

    public Receptionist() {
        this.emailAddress = "";
        this.password = "";
    }

    public Receptionist(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean logIn(TextField loginEmail, PasswordField loginPass, Label errorMessage){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT `email address`, `password` FROM `hospital`.`logininfo`;\n";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()){
                emailAddress = queryOutput.getString("email address");
                password = queryOutput.getString("password");

                if(emailAddress.equals(loginEmail.getText()) && password.equals(loginPass.getText())){
                    errorMessage.setTextFill(Color.web("#61cb34"));
                    errorMessage.setText("Login Successful!");
                    successfulLogIn = true;
                    break;
                }

            }

            if(!successfulLogIn){
                errorMessage.setText("Wrong user name or password");
            }

        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulLogIn;
    }

    public boolean signUp(TextField signUEmail, PasswordField signUpPass, PasswordField signUpConfirmPass, Label errorMessage){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        emailAddress = signUEmail.getText();
        password = signUpPass.getText();
        if(!password.equals(signUpConfirmPass.getText())){
            errorMessage.setText("Passwords don't match");
            return false;
        }

        String connectQuery = "INSERT INTO `hospital`.`logininfo`(`email address`,`password`) values (?,?);\n";

        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1,emailAddress);
            statement.setString(2,password);
            int status = statement.executeUpdate();

            if(status==1){
                errorMessage.setTextFill(Color.web("#61cb34"));
                errorMessage.setText("User Signed In!");
                signUEmail.setText("");
                signUpPass.setText("");
                signUpConfirmPass.setText("");
            }
            else{
                errorMessage.setText("SignUp Failed");
            }



        } catch (Exception s){
            s.printStackTrace();
        }
        return successfulSignUp;

    }

}
