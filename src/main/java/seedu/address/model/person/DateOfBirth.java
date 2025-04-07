package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's Date of Birth in the address book.
 * Guarantees: immutable; is always valid
 */
public class DateOfBirth {
    public static final String MESSAGE_CONSTRAINTS = "Date of Birth should be in the format: dd/MM/yyyy and year "
            + "should be after 1900, and date of birth cannot be after today's date.";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(java.time.format.ResolverStyle.STRICT);
    public final LocalDate value;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid Date of Birth.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDate(dateOfBirth), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(dateOfBirth, formatter);
    }

    /**
     * Returns true if a given string is a valid Date of Birth.
     */
    public static boolean isValidDate(String test) {
        LocalDate inputDate;
        try {
            inputDate = LocalDate.parse(test, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        if (inputDate.isBefore(LocalDate.of(1900, 1, 1)) || inputDate.isAfter(LocalDate.now())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
