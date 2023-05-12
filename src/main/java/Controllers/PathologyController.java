package Controllers;

import Models.DBUtils;
import Models.Medicine;
import Models.Pathology;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class PathologyController implements Initializable {


    @FXML
    public TableView<Pathology> PathologyTable;
    @FXML
    public TableColumn<Pathology, String> TestID;
    @FXML
    public TableColumn<Pathology, String> TestName;
    @FXML
    public TableColumn<Pathology, String> TestPrice;
    @FXML
    public TableColumn<Pathology, String> Testdescription;
    @FXML
    public Button selectButton;
    @FXML
    public Button removeButton;
    @FXML
    public Button logOutButton;
    @FXML
    public Button PathologyConfirmButton;
    ObservableList<Pathology> testList = FXCollections.observableArrayList();


    public void searchPathology(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchPathologyQuery = "SELECT `ID`, `Test Name`, `Test Description`, `Price` FROM hospital.Pathologyinfo ;\n";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchPathologyQuery);

            while(queryOutput.next()){
                String testID = queryOutput.getString("ID");
                String testname = queryOutput.getString("Test Name");
                String testDescription = queryOutput.getString("Test Description");
                Double testprice = queryOutput.getDouble("Price");

                testList.add(new Pathology(testID,testname,testDescription,testprice));
            }


            TestID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            TestName.setCellValueFactory(new PropertyValueFactory<>("testName"));
            Testdescription.setCellValueFactory(new PropertyValueFactory<>("testDescription"));
            TestPrice.setCellValueFactory(new PropertyValueFactory<>("testPrice"));

            PathologyTable.setItems(testList);

            //filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
