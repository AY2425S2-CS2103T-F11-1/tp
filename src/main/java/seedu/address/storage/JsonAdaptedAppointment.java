package seedu.address.storage;

import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    private final String appointmentDateTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentDateTime = source.toString();
    }

    /**
     * Gets the string representation of the appointment for Jackson serialization.
     * This instructs Jackson to serialize the appointment as a simple string.
     */
    @JsonValue
    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (appointmentDateTime == null) {
            throw new IllegalValueException("Appointment date time is missing!");
        }

        try {
            return Appointment.createAppointment(appointmentDateTime);
        } catch (DateTimeParseException e) {
            // Return null appointment
            return null;
        }
    }
}
