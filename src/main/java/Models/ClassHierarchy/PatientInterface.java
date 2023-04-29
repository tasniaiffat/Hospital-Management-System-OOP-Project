package Models.ClassHierarchy;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.util.List;

public interface PatientInterface {
    public List<String> getMedicalHistory();
    public void setMedicalHistory(List<String> medicalHistory);
    public List<String> getCurrentTreatment();
    public void setCurrentTreatment(List<String> currentTreatment);
    public boolean addPatient(ActionEvent e, Label errorMessage);
    public boolean removePatient(ActionEvent e, Label errorMessage);
}
