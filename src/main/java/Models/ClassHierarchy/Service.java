package Models.ClassHierarchy;

import Models.Patient;

import java.time.LocalDate;

public abstract class Service {
    protected String ID;
    private Patient patient;
    private LocalDate dateOfService;
    protected String medicineName;
    protected String medicineCompany;
    protected double medicinePrice;
    protected int quantityAvailable;
    private int totalBill;

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
    public void setMedicineName(String name){this.medicineName = name;}
    public String getMedicineName(){return medicineName;}
    public void setMedicineCompany(String company){ this.medicineCompany = company;}
    public String getMedicineCompany(){return this.medicineCompany;}
    public double getMedicinePrice(){return medicinePrice;}
    public void setMedicinePrice(double price){this.medicinePrice = price;}
    public void setQuantityAvailable(int ava){this.quantityAvailable = ava;}
    public int getQuantityAvailable(){return this.quantityAvailable;}

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
