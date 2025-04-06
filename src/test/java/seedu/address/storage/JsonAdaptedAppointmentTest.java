package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;

public class JsonAdaptedAppointmentTest {

    private static final String VALID_DATETIME = "01/01/2025 15:00";
    private static final String INVALID_DATETIME = "invalid-datetime";
    private static final String NULL_DATETIME = null;

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        Appointment appointment = Appointment.createAppointment(VALID_DATETIME);
        JsonAdaptedAppointment jsonAppointment = new JsonAdaptedAppointment(appointment);
        assertEquals(appointment.toString(), jsonAppointment.toModelType().toString());
    }

    @Test
    public void toModelType_invalidAppointmentDateTime_returnsNull() {
        JsonAdaptedAppointment jsonAppointment = new JsonAdaptedAppointment(INVALID_DATETIME);
        try {
            assertNull(jsonAppointment.toModelType());
        } catch (IllegalValueException e) {
            // Do nothing
        }
    }

    @Test
    public void toModelType_nullAppointment_throwsIllegalValueException() {
        JsonAdaptedAppointment jsonAppointment = new JsonAdaptedAppointment(NULL_DATETIME);
        assertThrows(IllegalValueException.class, jsonAppointment::toModelType);
    }
}
