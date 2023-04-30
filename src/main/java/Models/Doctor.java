package Models;

import Models.ClassHierarchy.DoctorInterface;
import Models.ClassHierarchy.Gender;
import Models.ClassHierarchy.Person;

import java.time.LocalDate;

public class Doctor extends Person implements DoctorInterface{
    private String qualification;
    private String speciality;

    private boolean isAvailable;

    public Doctor(String name, String contactNo, String emailAddress, String address, LocalDate date, Gender gender, String qualification, String speciality, boolean isAvailable) {
        super(name, contactNo, emailAddress, address, date, gender);
        this.qualification = qualification;
        this.speciality = speciality;
        this.isAvailable = isAvailable;
    }

    public Doctor() {}

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}

