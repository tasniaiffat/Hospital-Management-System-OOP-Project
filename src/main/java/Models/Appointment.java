package Models;
import Models.ClassHierarchy.AppointmentInterface;
import Models.ClassHierarchy.PaymentStatus;
import Models.ClassHierarchy.Service;
import static Controllers.BillingScreenController.bill;


public class Appointment extends Service implements AppointmentInterface {
    private Doctor doctor;

    public boolean scheduleAppointment(){
        bill.increaseBillingAmount(1000);
        bill.setPaymentStatus(PaymentStatus.Due);
        return true;
    }

    @Override
    public boolean provideService() {
        scheduleAppointment();
        return true;
    }
}
