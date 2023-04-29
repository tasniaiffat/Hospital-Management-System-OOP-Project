package Models.ClassHierarchy;

import Models.Patient;

import java.time.LocalDate;

public abstract class Service {
    private String ID;
    private Patient patient;
    private LocalDate dateOfService;

    public Service(String ID, Patient patient, LocalDate dateOfService) {
        this.ID = ID;
        this.patient = patient;
        this.dateOfService = dateOfService;
    }

    public Service() {}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(LocalDate dateOfService) {
        this.dateOfService = dateOfService;
    }

    public boolean provideService(){
        return true;
    }
}
