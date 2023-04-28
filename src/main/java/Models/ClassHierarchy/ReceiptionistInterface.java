package Models.ClassHierarchy;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public interface ReceiptionistInterface {

    public String getEmailAddress();
    public void setEmailAddress(String emailAddress);
    public String getPassword();
    public void setPassword(String password);
    public boolean logIn(TextField loginEmail, PasswordField loginPass, Label errorMessage);
    public boolean signUp(TextField signUEmail, PasswordField signUpPass, PasswordField signUpConfirmPass, Label errorMessage);
}
