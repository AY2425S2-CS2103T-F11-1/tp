package seedu.address.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {
    private LocalDateTime apptDateTime;

    private Appointment(LocalDateTime parsedDateTime) {
        this.apptDateTime = parsedDateTime;
    }

    public static Appointment createAppointment(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
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
