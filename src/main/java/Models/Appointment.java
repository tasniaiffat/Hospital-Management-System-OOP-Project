package Models;
import Models.ClassHierarchy.AppointmentInterface;
import Models.ClassHierarchy.Service;
import Controllers.BillingScreenController;


public class Appointment extends Service implements AppointmentInterface {
    private Doctor doctor;

    public boolean scheduleAppointment(){
        return true;
    }

    @Override
    public boolean provideService() {
        scheduleAppointment();
        return true;
    }
}
