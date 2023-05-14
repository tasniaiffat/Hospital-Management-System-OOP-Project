package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

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
    private TableView<Pathology> PathologyTable;
    @FXML
    private TableColumn<Pathology, String> TestID;
    @FXML
    private TableColumn<Pathology, String> TestName;
    @FXML
    private TableColumn<Pathology, String> TestPrice;
    @FXML
    private TableColumn<Pathology, String> Testdescription;
    @FXML
    private Button selectButton;
    @FXML
    private TextField chooseLabTestTxtField;

    @FXML
    private Button backButton;

    @FXML
    private Button PathologyConfirmButton;

    @FXML
    private Label errorMessage;
    ObservableList<Pathology> testList = FXCollections.observableArrayList();

    public static String chosenID  = null;


    public void searchPathology(){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();

        String searchPathologyQuery = "SELECT `ID`, `Test Name`, `Test Description`, `Price` FROM hospital.pathologyinfo ;\n";

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

            filterList();


        } catch (Exception e){
            Logger.getLogger(ChoosePatientScreenController.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    public void filterList(){
        FilteredList<Pathology> filteredList = new FilteredList<>(testList, b -> true);

        chooseLabTestTxtField.textProperty().addListener( (observable, oldValue, newValue) -> {
            filteredList.setPredicate( Pathology -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if(Pathology.getTestName().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Pathology.getID().toLowerCase().contains(searchKeyword)){
                    return true;
                } else if(Pathology.getTestDescription().toLowerCase().contains(searchKeyword)){
                    return true;
                } else return false;

            });
        });

        SortedList<Pathology> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(PathologyTable.comparatorProperty());

        PathologyTable.setItems(testList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchPathology();

        selectButton.setOnAction( e -> {
            Pathology selectedtest = PathologyTable.getSelectionModel().getSelectedItem();
            if (selectedtest != null) {
                String ID = selectedtest.getID();
                chosenID = ID;
                if(selectedtest!=null){
                    boolean provided = selectedtest.provideService();
                    if(!provided){
                        errorMessage.setText("Service could not be provided");
                    }
                    else {
                        errorMessage.setTextFill(Color.web("#61cb34"));
                        errorMessage.setText("Lab Test Added");
                    }
                }
            }
            else{
                errorMessage.setText("Medicine Not Selected");
            }
        });



        backButton.setOnAction( e -> {
            ManagementUtils.changeScence(e, "ReceptionScreen.fxml","Reception");
        });



    }
}
