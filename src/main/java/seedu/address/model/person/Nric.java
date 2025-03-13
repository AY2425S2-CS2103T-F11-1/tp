package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's NRIC in the address book.
 * Guarantees: immutable; is always valid
 */
public class Nric {
    public final String value;

    /**
     * Constructs a {@code Nric}.
     *
     * @param icNumber A valid nric.
     */
    public Nric(String icNumber) {
        requireNonNull(icNumber);
        value = icNumber;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && value.equals(((Nric) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
