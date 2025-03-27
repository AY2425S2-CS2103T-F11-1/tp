package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppointmentListTest {

    @Test
    public void addAppointment_validString_success() {
        AppointmentList appointmentList = new AppointmentList();
        String appointmentStr = "01/01/2025 15:00";
        appointmentList.addAppointment(appointmentStr);

        assertEquals(1, appointmentList.getAppointments().size());
        assertTrue(appointmentList.getAppointments().get(0).toString().equals(appointmentStr));
    }

    @Test
    public void addAppointment_validAppointment_success() {
        AppointmentList appointmentList = new AppointmentList();
        Appointment appointment = Appointment.createAppointment("01/01/2025 15:00");
        appointmentList.addAppointment(appointment);

        assertEquals(1, appointmentList.getAppointments().size());
        assertEquals(appointment, appointmentList.getAppointments().get(0));
    }

    @Test
    public void removeAppointment_validIndex_success() {
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.addAppointment("01/01/2025 15:00");
        appointmentList.addAppointment("02/01/2025 16:00");

        appointmentList.removeAppointment(1);
        assertEquals(1, appointmentList.getAppointments().size());
        assertTrue(appointmentList.getAppointments().get(0).toString().equals("02/01/2025 16:00"));
    }

    @Test
    public void sortAppointments_multipleAppointments_chronologicalOrder() {
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.addAppointment("02/01/2025 16:00"); // Add later appointment first
        appointmentList.addAppointment("01/01/2025 15:00"); // Add earlier appointment second

        // Should be sorted chronologically
        assertEquals("01/01/2025 15:00", appointmentList.getAppointments().get(0).toString());
        assertEquals("02/01/2025 16:00", appointmentList.getAppointments().get(1).toString());
    }
}
