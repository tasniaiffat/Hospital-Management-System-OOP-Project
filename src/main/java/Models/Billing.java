package Models;

import Models.ClassHierarchy.PaymentStatus;
import Models.ClassHierarchy.Service;

import java.time.LocalDate;

public class Billing extends Service{
    private double billingAmount;
    private PaymentStatus paymentStatus;


    public Billing() {
        billingAmount=0;
        paymentStatus = PaymentStatus.Paid;
    }

    public Billing(Patient patient, LocalDate dateOfService, double billingAmount) {
        super(patient, dateOfService);
        this.billingAmount = billingAmount;
        this.ID = generateID(patient);
    }


    public String generateID(Patient patient){
        String IDNum = patient.getID().substring(3);
        return "BIL"+IDNum;
    }

    public double getBillingAmount() {
        return billingAmount;
    }

    public void increaseBillingAmount(double billingAmount) {
        this.billingAmount+=billingAmount;
    }

    public void setBillingAmount(double newamount) {
        this.billingAmount = billingAmount+=newamount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean provideService() {
        paymentStatus = PaymentStatus.Paid;
        billingAmount = 0.00;
        return true;
    }
}
