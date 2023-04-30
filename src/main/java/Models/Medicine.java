package Models;

import Models.ClassHierarchy.MedicineInterface;
import Models.ClassHierarchy.Service;

import java.time.LocalDate;

public class Medicine extends Service implements MedicineInterface {
    private String medicineName;
    private String medicineDescription;
    private double medicinePrice;
    private int quantityAvailable;

    public Medicine(String ID, Patient patient, LocalDate dateOfService, String medicineName,
                    String medicineDescription, double medicinePrice, int quantityAvailable) {
        super(ID, patient, dateOfService);
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
        this.medicinePrice = medicinePrice;
        this.quantityAvailable = quantityAvailable;
    }

    public Medicine() {}

    @Override
    public boolean addMedicine() {
        return true;
    }
}
