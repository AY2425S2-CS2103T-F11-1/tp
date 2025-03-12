package seedu.address.appointment;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;

public class AppointmentList {
    private ArrayList<Appointment> appointments;

    public AppointmentList() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String date) {
        try {
            Appointment a = Appointment.createAppointment(date);
            this.appointments.add(a);
            sortAppointments();
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy HH:mm.");
        }
    }

    public void removeAppointment(int i) {
        this.appointments.remove(i-1);
        sortAppointments();
    }

    private void sortAppointments() {
        appointments.sort(Comparator.comparing(Appointment::getApptDateTime));
    }

    @Override
    public String toString() {
        int count = 1;
        String s = "";
        for (Appointment a : this.appointments) {
            s = s + count + ". " + a.getApptDateTime() + "\n";
        }
        return s;
    }
}
