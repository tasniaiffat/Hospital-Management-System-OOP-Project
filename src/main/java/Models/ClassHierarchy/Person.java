package Models.ClassHierarchy;

import java.util.Date;

public abstract class Person {
    protected String ID;
    protected String name;
    protected String contactNo;
    protected String emailAddress;
    protected String address;
    protected Date date;
    protected Gender gender;

    public Person(String ID, String name, String contactNo, String emailAddress, String address, Date date, Gender gender) {
        this.ID = ID;
        this.name = name;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.address = address;
        this.date = date;
        this.gender = gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
