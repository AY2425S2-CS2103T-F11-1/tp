package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Appointment for the same Person
 * Appointments are considered to be the same if they start at the same date and time
 */
public class DuplicateAppointmentException extends RuntimeException {
    public DuplicateAppointmentException() {
        super("Operation would result in duplicate appointments.");
    }
}
