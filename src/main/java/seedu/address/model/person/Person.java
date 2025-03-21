package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.appointment.Appointment;
import seedu.address.appointment.AppointmentList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Nric nricNumber;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final AppointmentList appointmentList;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Nric nricNumber, Set<Tag> tags) {
        requireAllNonNull(name, phone, nricNumber);
        this.name = name;
        this.phone = phone;
        this.nricNumber = nricNumber;
        this.appointmentList = new AppointmentList();
        appointmentList.addAppointment("01/01/2025 15:00");
        appointmentList.addAppointment("02/02/2025 12:00");
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Nric getNric() {
        return nricNumber;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable appointment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Appointment> getAppointmentList() {
        return Collections.unmodifiableList(appointmentList.getAppointments());
    }

    /**
     * Returns true if both persons have the same NRIC.
     * This defines a weaker notion of equality between two persons.
     * Though 'weaker', this is functionally strong enough.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && nricNumber.equals(otherPerson.nricNumber)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, nricNumber, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("nric", nricNumber)
                .add("tags", tags)
                .toString();
    }

}
