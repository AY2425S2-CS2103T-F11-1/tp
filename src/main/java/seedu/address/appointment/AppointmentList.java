package seedu.address.appointment;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;

/** Represents a list of appointments. */
public class AppointmentList {
    private ArrayList<Appointment> appointments;

    public AppointmentList() {
        this.appointments = new ArrayList<>();
    }

    /**
     * Adds a new appointment to the list.
     * Sorts the updated list in chronological order.
     */
    public void addAppointment(String date) {
        try {
            Appointment a = Appointment.createAppointment(date);
            this.appointments.add(a);
            sortAppointments();
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy HH:mm.");
        }
    }

    /**
     * Removes a particular appointment from the list.
     * @param i is the index of the appointment.
     */
    public void removeAppointment(int i) {
        this.appointments.remove(i - 1);
        sortAppointments();
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
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
