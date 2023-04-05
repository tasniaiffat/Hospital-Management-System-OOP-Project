package Models;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagementUtils {
    public static void changeScence(ActionEvent event, String fxmlFile, String Title){
        Parent root=null;
        try{
            root = FXMLLoader.load(ManagementUtils.class.getResource(fxmlFile));

        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(Title);
        stage.setScene(new Scene(root,1000,680));
        stage.show();
    }
}
