package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC in the address book.
 * Guarantees: immutable; is always valid
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS =
            "NRICs should only contain alphabets or numbers, and it should start with S,T,F,G followed by "
                    + "7 digits, and ending with an alphabet";
    //NRIC regex reference: https://stackoverflow.com/questions/29743154/regular-expression-for-nric-fin-in-singapore
    public static final String VALIDATION_REGEX = "^[STFGMstfgm]\\d{7}[A-Za-z]$";
    public final String value;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid NRIC number.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        value = nric.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid NRIC number.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
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
