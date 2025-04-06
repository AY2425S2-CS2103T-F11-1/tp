package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void equalAppointment_success() {
        Appointment testAppointment = Appointment.createAppointment("09/09/2029 15:00");
        assertEquals(testAppointment, Appointment.createAppointment("09/09/2029 15:00"));
    }

    @Test
    public void notEqualAppointment_success() {
        Appointment testAppointment = Appointment.createAppointment("09/09/2029 15:00");
        assertNotEquals(testAppointment, Appointment.createAppointment("09/09/2029 15:01"));
    }

}
