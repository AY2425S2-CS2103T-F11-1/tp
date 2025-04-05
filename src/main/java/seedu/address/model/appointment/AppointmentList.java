package seedu.address.model.appointment;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.model.person.exceptions.DuplicateAppointmentException;

/** Represents a list of appointments. */
public class AppointmentList {
    private final List<Appointment> appointments;

    public AppointmentList() {
        this.appointments = new ArrayList<>();
    }

    /**
     * Adds a new appointment to the list.
     * Sorts the updated list in chronological order.
     */
    public void addAppointment(String date) throws DuplicateAppointmentException {
        Appointment a = Appointment.createAppointment(date);
        if (this.appointments.contains(a)) {
            throw new DuplicateAppointmentException();
        }
        this.appointments.add(a);
        sortAppointments();
    }

    /**
     * Adds an existing appointment to the list.
     * Used by storage to reconstruct appointments.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Removes a particular appointment from the list.
     * @param i is the index of the appointment.
     */
    public void removeAppointment(int i) {
        this.appointments.remove(i - 1);
        sortAppointments();
    }

    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    public int getSize() {
        return this.appointments.size();
    }

    /**
     * @return True if the appointment already exists in the list
     */
    public boolean hasAppointment(Appointment a) {
        return this.appointments.contains(a);
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
