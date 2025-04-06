package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Represents an appointment */
public class Appointment {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(java.time.format.ResolverStyle.STRICT);
    private LocalDateTime apptDateTime;

    private Appointment(LocalDateTime parsedDateTime) {
        this.apptDateTime = parsedDateTime;
    }

    /**
     * Creates a new appointment.
     * Throws {@code DateTimeParseException} if the date and time is invalid.
     */
    public static Appointment createAppointment(String date) throws DateTimeParseException {
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, formatter);
        return new Appointment(parsedDateTime);
    }

    public LocalDateTime getApptDateTime() {
        return this.apptDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return apptDateTime.format(formatter);
    }
}
