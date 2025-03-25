package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's Date of Birth in the address book.
 * Guarantees: immutable; is always valid
 */
public class DateOfBirth {
    public final LocalDate value;

    /**
     * Constructs a {@code Nric}.
     *
     * @param dateOfBirth A valid nric.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        value = LocalDate.parse(dateOfBirth);
    }

    @Override
    public String toString() {
        //return value.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return value.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && value.equals(((DateOfBirth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
